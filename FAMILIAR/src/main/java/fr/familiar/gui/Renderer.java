/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for
 * manIpulation and Automatic Reasoning) project (2010-2017)
 * http://familiar-project.github.com/
 *
 * FAMILIAR is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>
 */
package fr.familiar.gui;

import gsd.synthesis.FeatureEdge;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import prefuse.Constants;
import prefuse.data.Edge;
import prefuse.data.Node;
import prefuse.data.Tuple;
import prefuse.data.expression.AbstractPredicate;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.EdgeRenderer;
import prefuse.render.LabelRenderer;
import prefuse.render.ShapeRenderer;
import prefuse.util.GraphicsLib;
import prefuse.visual.EdgeItem;
import prefuse.visual.VisualItem;
import prefuse.visual.expression.InGroupPredicate;

public class Renderer {
	// A simple, fast, and thread-safe singleton implementation.
	public final static Renderer INSTANCE = new Renderer();
	
	private LabelRenderer nodeRenderer;
	private EdgeRenderer edgeGroupRenderer;
	private EdgeRenderer edgeMandRenderer;
	private EdgeRenderer edgeOptRenderer;

	private Renderer() {
	}
	
	DefaultRendererFactory getDRF(String label) {
    	nodeRenderer = new LabelRenderer(label);
        nodeRenderer.setHorizontalAlignment(Constants.CENTER);
        
        edgeGroupRenderer = new EdgeRenderer(Constants.EDGE_TYPE_LINE);
        edgeGroupRenderer.setHorizontalAlignment1(Constants.CENTER);
        edgeGroupRenderer.setHorizontalAlignment2(Constants.CENTER);
        edgeGroupRenderer.setVerticalAlignment1(Constants.BOTTOM);
        edgeGroupRenderer.setVerticalAlignment2(Constants.TOP);
		
        edgeMandRenderer = new FMEdgeRenderer(Converter.MANDATORY);
        edgeMandRenderer.setHorizontalAlignment1(Constants.CENTER);
        edgeMandRenderer.setHorizontalAlignment2(Constants.CENTER);
        edgeMandRenderer.setVerticalAlignment1(Constants.BOTTOM);
        edgeMandRenderer.setVerticalAlignment2(Constants.TOP);
        
		edgeOptRenderer = new FMEdgeRenderer(Converter.OPTIONAL);
		edgeOptRenderer.setHorizontalAlignment1(Constants.CENTER);
		edgeOptRenderer.setHorizontalAlignment2(Constants.CENTER);
		edgeOptRenderer.setVerticalAlignment1(Constants.BOTTOM);
		edgeOptRenderer.setVerticalAlignment2(Constants.TOP);
		
		DefaultRendererFactory drf = new DefaultRendererFactory(nodeRenderer);
		drf.add(new GPredicate(FeatureEdge.OR), new OrGroupRenderer());
		drf.add(new GPredicate(FeatureEdge.XOR), new XorGroupRenderer());
		drf.add(new Predicate(Converter.OPTIONAL), edgeOptRenderer);
		drf.add(new Predicate(Converter.MANDATORY), edgeMandRenderer);
		drf.add(new InGroupPredicate(FamiliarEditor.treeEdges), edgeGroupRenderer);
		return drf;
	}
	
	class Predicate extends AbstractPredicate {
		private int predType;
	    public Predicate(int pType) {
	    	predType = pType;
	    }
	    @Override
	    public boolean getBoolean(Tuple t) {
			if (t instanceof Edge) {
				Edge edge = (Edge) t;
				return edge.getTargetNode().getInt(Converter.SOLITARY) == predType;
			} else {
				return false;
			}
	    }
	}
	

	class FMEdgeRenderer extends EdgeRenderer {
		protected Shape m_arrowHead = new Ellipse2D.Float(-5.0f, -8.0f, 9.0f, 9.0f);
		private int m_arrowType;
	
	    public FMEdgeRenderer(int arrowType) {
	        super();  
	        m_arrowType = arrowType;
	    }
		@Override
		public void render(Graphics2D g, VisualItem item) {
			// Do not call super.render() (EdgeRenderer.render())
	        // because it uses g.fill to render the default polygon arrowhead
	        Shape shape = getShape(item);
	        if (shape != null) {
	            drawShape(g, item, shape);
	        }
	        // Render the edge arrow head
	        if (m_curArrow != null) {
	            if (m_arrowType == Converter.MANDATORY) {
	            	g.fill(m_curArrow);
	            }
	            g.draw(m_curArrow);
	        }
	    }
		@Override
		protected Shape getRawShape(VisualItem item) {
	        EdgeItem   edge = (EdgeItem)item;
	        VisualItem item1 = edge.getSourceItem();
	        VisualItem item2 = edge.getTargetItem();
	        
	        getAlignedPoint(m_tmpPoints[0], item1.getBounds(),
	                        m_xAlign1, m_yAlign1);
	        getAlignedPoint(m_tmpPoints[1], item2.getBounds(),
	                        m_xAlign2, m_yAlign2);
	        m_curWidth = (float)(m_width * getLineWidth(item));
	       
	        // create the arrow head, if needed
	        EdgeItem e = (EdgeItem)item;
	      
            boolean forward = (m_edgeArrow == Constants.EDGE_ARROW_FORWARD);
          
            Point2D start = null, end = null;
            start = m_tmpPoints[forward?0:1];
            end   = m_tmpPoints[forward?1:0];
            
            // compute the intersection with the target bounding box
            VisualItem dest = forward ? e.getTargetItem() : e.getSourceItem();
            int i = GraphicsLib.intersectLineRectangle(start, end,
                    dest.getBounds(), m_isctPoints);
            if ( i > 0 ) end = m_isctPoints[0];
           
            // create the arrow head shape
            AffineTransform at = getArrowTrans(start, end, m_curWidth);
            m_curArrow = at.createTransformedShape(m_arrowHead);
            
            // Shorten the edge so that empty circle is not crossed
            Point2D lineEnd = m_tmpPoints[forward?1:0]; 
            lineEnd.setLocation(0, -m_arrowHeight+5);
            at.transform(lineEnd, lineEnd);
	        
	        // create the edge shape
	        double n1x = m_tmpPoints[0].getX();
	        double n1y = m_tmpPoints[0].getY();
	        double n2x = m_tmpPoints[1].getX();
	        double n2y = m_tmpPoints[1].getY();
	        m_line.setLine(n1x, n1y, n2x, n2y);
	        return m_line;
	    }
	}
	
	class GPredicate extends AbstractPredicate {
		private int predType;
	    public GPredicate(int pType) {
	    	predType = pType;
	    }
	    @Override
	    public boolean getBoolean(Tuple t) {
	        if (t instanceof Node) {
	            Node n = (Node) t;
	            return n.getInt(Converter.GROUP) == predType;
	        } else {
	            return false;
	        }
	    }
	 }

	class VariabilityGroupRenderer extends ShapeRenderer {
		private static final int WIDTH = 30;
		private static final int HEIGHT = 20;
		
		public VariabilityGroupRenderer() {
		    super();
		}
		@Override
		protected Shape getRawShape(VisualItem item) {
			Shape tUp = null;
			try {
				if (item.getSourceTuple() instanceof Node) {
					Node node = (Node) item.getSourceTuple();
					if ((node.getParent()).getChildCount() > 1) {
						tUp = triangle_up ((float) (item.getX() - WIDTH / 3), 
							(float)(item.getY() - WIDTH / 3), (float) HEIGHT);
					}
				} 
				tUp = triangle_up ((float) (item.getX()-7), (float)(item.getY()-27), (float)15);
			} catch (Exception e) {
	    		// Ignore this exception
	    	}
			return tUp;
		}
	}
	 
	class OrGroupRenderer extends VariabilityGroupRenderer {
	    public OrGroupRenderer() {
	        super();
	    }
	    @Override
	    protected void drawShape(Graphics2D g, VisualItem item, Shape shape) {
	    	item.setFillColor(Color.BLACK.getRGB()); 
	    	super.drawShape(g, item, shape);
	    }
	}
	  
	class XorGroupRenderer extends VariabilityGroupRenderer {
		public XorGroupRenderer() {
	        super();
	    }
		@Override
		protected void drawShape(Graphics2D g, VisualItem item, Shape shape) {
	    	item.setFillColor(Color.WHITE.getRGB());
	    	item.setStrokeColor(Color.BLACK.getRGB());
	    	super.drawShape(g, item, shape);
		}
	}
} // end of class Renderer
