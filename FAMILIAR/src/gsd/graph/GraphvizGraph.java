/*
 * This file is part of the Feature Model Synthesis project (FMSynth).
 *
 * Copyright (C) 2010 Steven She <shshe@gsd.uwaterloo.ca>
 *
 * FMSynth is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * FMSynth is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FMSynth.  (See files COPYING and COPYING.LESSER.)  If not, see
 * <http://www.gnu.org/licenses/>.
 */

package gsd.graph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Provides a toString() method for outputting a DirectedGraph to the GraphViz
 * format.
 *
 * @author Steven She <shshe@uwaterloo.ca>
 *
 * @param <V>
 *            The graph vertex type
 * @param <E>
 *            The graph edge type
 */
public class GraphvizGraph<V, E> {

  public static String OUTPUT_FORMAT = "pdf";

  protected BasicGraph<V, E> g;
  protected IGraphvizLabelProvider<E> mEdgeLbl = new IGraphvizLabelProvider<E>() {
    public String getLabel(E edge) {
      return "";
    }
  };
  protected IGraphvizLabelProvider<V> mVertexLbl = new IGraphvizLabelProvider<V>() {
    public String getLabel(V vertex) {
      return vertex.toString().replace("\"", "\\\"");
    }
  };
  protected GraphvizProperties<V, E> mProps = new GraphvizProperties<V, E>();

  //FIXME better to make these just key of GraphvizProperties
  protected RankDir mDir = RankDir.TOP_BOTTOM;
  protected ArrowType mArrowType = ArrowType.NORMAL;
  protected Shape mShape = Shape.BOX;

  public static enum ArrowType {
    NONE {
      public String toString() {
        return "none";
      }
    },
    NORMAL {
      public String toString() {
        return "normal";
      }
    }
  }
  public static enum RankDir {
    TOP_BOTTOM {
      public String toString() {
        return "TB";
      }
    },
    BOTTOM_TOP {
      public String toString() {
        return "BT";
      }
    },
    LEFT_RIGHT {
      public String toString() {
        return "LR";
      }
    },
    RIGHT_LEFT {
      public String toString() {
        return "RL";
      }
    }
  }
  public static enum Shape {
    BOX {
      public String toString() {
        return "box";
      }
    },
    ELLIPSE {
      public String toString() {
        return "ellipse";
      }
    },
    CIRCLE {
      public String toString() {
        return "circle";
      }
    },
    TRIANGLE {
      public String toString() {
        return "triangle";
      }
    }
  }

  public GraphvizGraph(BasicGraph<V, E> graph) {
    this.g = graph;
  }

  public GraphvizGraph(BasicGraph<V, E> graph, GraphvizProperties<V, E> props) {
    this.g = graph;
    this.mProps = props;
  }


  public void save(File dotFile) throws IOException, InterruptedException {
    save(toString(), dotFile);
  }

  public static void save(String text, File dotFile) throws IOException, InterruptedException {
    if (dotFile.exists()) {
      dotFile.delete();
    }

    BufferedWriter out = new BufferedWriter(new FileWriter(dotFile));
    out.write(text);
    out.close();

    String fileName = dotFile.getAbsoluteFile().toString();
    String diagFile = fileName.substring(0, fileName.lastIndexOf(".")) + "." + OUTPUT_FORMAT;

    String command = "dot -T" + OUTPUT_FORMAT + " -o " + diagFile
      + " " + dotFile.getAbsoluteFile().toString();

    System.out.println("Executing: " + command);

    Process p = Runtime.getRuntime().exec(command);
    p.waitFor();
    if (p.exitValue() > 0) {
      throw new UnsupportedOperationException(dotFile.getAbsoluteFile().toString());
    }
  }

  @Override
  public String toString() {
    StringBuilder b = new StringBuilder();
    b.append("digraph{\n");
    b.append("graph [");
    b.append("rankdir=" + mDir);
    b.append("];\n");
    b.append("node [shape=" + mShape + "];\n");
    b.append("edge [arrowhead=" + mArrowType + "];\n");

    Map<V, Integer> vertexIdMap = new HashMap<V, Integer>();
    Iterator<? extends V> iter = g.vertices().iterator();
    for (int i = 0; iter.hasNext(); i++) {
      V vertex = iter.next();
      vertexIdMap.put(vertex, i);

      b.append(i);
      b.append("[label=\"");
      b.append(mVertexLbl.getLabel(vertex));
      b.append("\",");
      b.append(mProps.getVertexPropertiesString(vertex));
      b.append("]\n");
    }

    Iterator<? extends E> edgeIter = g.edges().iterator();
    while (edgeIter.hasNext()) {
      E e = edgeIter.next();

      V source = g.getSource(e);
      V target = g.getTarget(e);

      b.append(vertexIdMap.get(source));
      b.append("->");
      b.append(vertexIdMap.get(target));
      b.append("[label=\"");
      b.append(mEdgeLbl.getLabel(e));
      b.append("\",");
      b.append(mProps.getEdgePropertiesString(e));
      b.append("]\n");
      
    }

    b.append("}");
    return b.toString();
  }

  public void setDirection(RankDir dir) {
    mDir = dir;
  }

  public void setArrowHead(ArrowType arrowType) {
    mArrowType = arrowType;
  }

  public void setShape(Shape shape) {
    mShape = shape;
  }

  public void setVertexLabelProvider(IGraphvizLabelProvider<V> prov) {
    this.mVertexLbl = prov;
  }

  public void setEdgeLabelProvider(IGraphvizLabelProvider<E> prov) {
    this.mEdgeLbl = prov;
  }

  public GraphvizProperties<V, E> getProperties() {
    return mProps;
  }
}
