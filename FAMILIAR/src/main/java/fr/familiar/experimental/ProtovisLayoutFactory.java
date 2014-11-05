/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project.
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.familiar.experimental;

/**
 * @author mathieuacher
 * 
 */
public class ProtovisLayoutFactory {

	public static String mkLayout(ProtovisLayout layout) {
		if (layout == ProtovisLayout.SUNBURST)
			return getSunburstLayout();
		else if (layout == ProtovisLayout.DENDOGRAMS)
			return getDendogramsLayout();
		else if (layout == ProtovisLayout.ICICLE)
			return getIcicleLayout();
		else if (layout == ProtovisLayout.INDENTED)
			return getIndentedLayout();
		else if (layout == ProtovisLayout.TREE)
			return getTreeLayout();
		else if (layout == ProtovisLayout.ENCLOSURE)
			return getEnclosureLayout();
		else if (layout == ProtovisLayout.TREEMAP)
			return getTreemapLayout();
		else
			return ""; // TODO error-handling
	}

	private static String getTreemapLayout() {

		return "/** Computes the full class name of a given node. */\n"
				+ "function title(d) {\n"
				+ "  return d.parentNode ? (title(d.parentNode) + \".\" + d.nodeName) : d.nodeName;\n"
				+ "}\n"
				+ "\n"
				+ "var re = \"\",\n"
				+ "    color = pv.Colors.category19().by(function(d) d.parentNode.nodeName)\n"
				+ "    nodes = pv.dom(flare).root(\"flare\").nodes();\n"
				+ "\n"
				+ "var vis = new pv.Panel()\n"
				+ "    .width(860)\n"
				+ "    .height(568);\n"
				+ "\n"
				+ "var treemap = vis.add(pv.Layout.Treemap)\n"
				+ "    .nodes(nodes)\n"
				+ "    .round(true);\n"
				+ "\n"
				+ "treemap.leaf.add(pv.Panel)\n"
				+ "    .fillStyle(function(d) color(d).alpha(title(d).match(re) ? 1 : .2))\n"
				+ "    .strokeStyle(\"#fff\")\n"
				+ "    .lineWidth(1)\n"
				+ "    .antialias(false);\n"
				+ "\n"
				+ "treemap.label.add(pv.Label)\n"
				+ "    .textStyle(function(d) pv.rgb(0, 0, 0, title(d).match(re) ? 1 : .2));\n"
				+ "\n"
				+ "vis.render();\n"
				+ "\n"
				+ "/** Counts the number of matching classes, updating the title element. */\n"
				+ "function count() {\n"
				+ "  var classes = 0, bytes = 0, total = 0;\n"
				+ "  for (var i = 0; i < nodes.length; i++) {\n"
				+ "    var n = nodes[i];\n"
				+ "    if(n.firstChild) continue;\n"
				+ "    total += n.nodeValue;\n"
				+ "    if (title(n).match(re)) {\n"
				+ "      classes++;\n"
				+ "      bytes += n.nodeValue;\n"
				+ "    }\n"
				+ "  }\n"
				+ "  var percent = bytes / total * 100;\n"
				+ "}\n"
				+ "\n"
				+ "/** Updates the visualization and count when a new query is entered. */\n"
				+ "function update(query) {\n" + "  if (query != re) {\n"
				+ "    re = new RegExp(query, \"i\");\n" + "    count();\n"
				+ "    vis.render();\n" + "  }\n" + "}\n" + "\n" + "count();\n"
				+ "";

	}

	private static String getEnclosureLayout() {
		return "/* For pretty number formatting. */\n"
				+ "var format = pv.Format.number();\n"
				+ "\n"
				+ "var vis = new pv.Panel()\n"
				+ "    .width(796)\n"
				+ "    .height(796)\n"
				+ "    .margin(2);\n"
				+ "\n"
				+ "var pack = vis.add(pv.Layout.Pack)\n"
				+ "    .nodes(pv.dom(flare).root(\"flare\").nodes())\n"
				+ "    .size(function(d) d.nodeValue);\n"
				+ "\n"
				+ "pack.node.add(pv.Dot)\n"
				+ "    .fillStyle(function(d) d.firstChild ? \"rgba(31, 119, 180, .25)\" : \"#ff7f0e\")\n"
				+ "    .title(function(d) d.nodeName + (d.firstChild ? \"\" : \": \" + format(d.nodeValue)))\n"
				+ "    .lineWidth(1);\n"
				+ "\n"
				+ "pack.label.add(pv.Label)\n"
				+ "    .visible(function(d) !d.firstChild)\n"
				+ "    .text(function(d) d.nodeName.substring(0, Math.sqrt(d.nodeValue) / 20));\n"
				+ "\n" + "vis.render();\n" + "";

	}

	private static String getIcicleLayout() {
		return "var vis = new pv.Panel()\n"
				+ "    .width(900)\n"
				+ "    .height(300)\n"
				+ "    .bottom(30);\n"
				+ "\n"
				+ "var layout = vis.add(pv.Layout.Partition.Fill)\n"
				+ "    .nodes(pv.dom(flare).root(\"flare\").nodes())\n"
				+ "    .order(\"descending\")\n"
				+ "    .orient(\"top\")\n"
				+ "    .size(function(d) d.nodeValue);\n"
				+ "\n"
				+ "layout.node.add(pv.Bar)\n"
				+ "    .fillStyle(pv.Colors.category19().by(function(d) d.parentNode && d.parentNode.nodeName))\n"
				+ "    .strokeStyle(\"rgba(255,255,255,.5)\")\n"
				+ "    .lineWidth(1)\n" + "    .antialias(false);\n" + "\n"
				+ "layout.label.add(pv.Label)\n"
				+ "    .textAngle(-Math.PI / 2)\n"
				+ "    .visible(function(d) d.dx > 6);\n" + "\n"
				+ "vis.render();\n" + "";
	}

	private static String getTreeLayout() {
		return "var vis = new pv.Panel()\n"
				+ "    .width(800)\n"
				+ "    .height(800)\n"
				+ "    .left(75)\n"
				+ "    .right(-75)\n"
				+ "    .top(-30)\n"
				+ "    .bottom(-80);\n"
				+ "\n"
				+ "var tree = vis.add(pv.Layout.Tree)\n"
				+ "    .nodes(pv.dom(flare).root(\"flare\").nodes())\n"
				+ "    .depth(85)\n"
				+ "    .breadth(7.25)\n"
				+ "    .orient(\"radial\");\n"
				+ "\n"
				+ "tree.link.add(pv.Line);\n"
				+ "\n"
				+ "tree.node.add(pv.Dot)\n"
				+ "    .fillStyle(function(n) n.firstChild ? \"#aec7e8\" : \"#ff7f0e\");\n"
				+ "\n" + "tree.label.add(pv.Label);\n" + "\n"
				+ "vis.render();\n" + "";
	}

	private static String getDendogramsLayout() {

		return "		var vis = new pv.Panel()\n"
				+ "		    .width(600)\n"
				+ "		    .height(1800)\n"
				+ "		    .left(40)\n"
				+ "		    .right(160)\n"
				+ "		    .top(10)\n"
				+ "		    .bottom(10);\n"
				+ "\n"
				+ "		var layout = vis.add(pv.Layout.Cluster)\n"
				+ "		    .nodes(pv.dom(flare)\n"
				+
				// "		        .root(\"flare\")\n" +
				// "		        .sort(function(a, b) pv.naturalOrder(a.nodeName, b.nodeName))\n"
				// +
				"		        .nodes())\n"
				+ "		    .group(true)\n"
				+
				// "		    .orient(\"top\");\n" +
				"		    .orient(\"left\");\n"
				+ "\n"
				+ "		layout.link.add(pv.Line)\n"
				+ "		    .strokeStyle(\"#ccc\")\n"
				+ "		    .lineWidth(1)\n"
				+ "		    .antialias(false);\n"
				+ "\n"
				+ "		layout.node.add(pv.Dot)\n"
				+ "		    .fillStyle(function(n) n.firstChild ? \"#aec7e8\" : \"#ff7f0e\");\n"
				+ "\n" + "		layout.label.add(pv.Label);\n" + "\n"
				+ "		vis.render();\n" + "\n";
	}

	private static String getSunburstLayout() {
		return "var vis = new pv.Panel()\n"
				+ "    .width(900)\n"
				+ "    .height(900)\n"
				+ "    .bottom(-80);\n"
				+ "\n"
				+ "var partition = vis.add(pv.Layout.Partition.Fill)\n"
				+ "    .nodes(pv.dom(flare).root(\"flare\").nodes())\n"
				+ "    .size(function(d) d.nodeValue)\n"
				+ "    .order(\"descending\")\n"
				+ "    .orient(\"radial\");\n"
				+ "\n"
				+ "partition.node.add(pv.Wedge)\n"
				+ "    .fillStyle(pv.Colors.category19().by(function(d) d.parentNode && d.parentNode.nodeName))\n"
				+ "    .strokeStyle(\"#fff\")\n"
				+ "    .lineWidth(.5);\n"
				+ "\n"
				+ "partition.label.add(pv.Label)\n"
				+ "    .visible(function(d) d.angle * d.outerRadius >= 6);\n"
				+ "\n"
				+ "vis.render();\n"
				+ "\n"
				+ "/* Update the layout's size method and re-render. */\n"
				+ "function update(method) {\n"
				+ "  switch (method) {\n"
				+ "    case \"byte\": partition.size(function(d) d.nodeValue); break;\n"
				+ "    case \"file\": partition.size(function(d) d.firstChild ? 0 : 1); break;\n"
				+ "  }\n" + "  vis.render();\n" + "}\n" + "\n";
	}

	private static String getIndentedLayout() {

		return "var root = pv.dom(flare)\n"
				+ "    .root(\"flare\")\n"
				+ "    .sort(function(a, b) pv.naturalOrder(a.nodeName, b.nodeName));\n"
				+ "\n"
				+ "/* Recursively compute the package sizes. */\n"
				+ "root.visitAfter(function(n) {\n"
				+ "  if (n.firstChild) {\n"
				+ "    n.nodeValue = pv.sum(n.childNodes, function(n) n.nodeValue);\n"
				+ "  }\n"
				+ "});\n"
				+ "\n"
				+ "var vis = new pv.Panel()\n"
				+ "    .width(260)\n"
				+ "    .height(function() (root.nodes().length + 1) * 12)\n"
				+ "    .margin(5);\n"
				+ "\n"
				+ "var layout = vis.add(pv.Layout.Indent)\n"
				+ "    .nodes(function() root.nodes())\n"
				+ "    .depth(12)\n"
				+ "    .breadth(12);\n"
				+ "\n"
				+ "layout.link.add(pv.Line);\n"
				+ "\n"
				+ "var node = layout.node.add(pv.Panel)\n"
				+ "    .top(function(n) n.y - 6)\n"
				+ "    .height(12)\n"
				+ "    .right(6)\n"
				+ "    .strokeStyle(null)\n"
				+ "    .events(\"all\")\n"
				+ "    .event(\"mousedown\", toggle);\n"
				+ "\n"
				+ "node.anchor(\"left\").add(pv.Dot)\n"
				+ "    .strokeStyle(\"#1f77b4\")\n"
				+ "    .fillStyle(function(n) n.toggled ? \"#1f77b4\" : n.firstChild ? \"#aec7e8\" : \"#ff7f0e\")\n"
				+ "    .title(function t(d) d.parentNode ? (t(d.parentNode) + \".\" + d.nodeName) : d.nodeName)\n"
				+ "  .anchor(\"right\").add(pv.Label)\n"
				+ "    .text(function(n) n.nodeName);\n"
				+ "\n"
				+ "node.anchor(\"right\").add(pv.Label)\n"
				+ "    .textStyle(function(n) n.firstChild || n.toggled ? \"#aaa\" : \"#000\")\n"
				+ "    .text(function(n) (n.nodeValue >> 10) + \"KB\");\n"
				+ "\n" + "vis.render();\n" + "\n"
				+ "/* Toggles the selected node, then updates the layout. */\n"
				+ "function toggle(n) {\n" + "  n.toggle(pv.event.altKey);\n"
				+ "  return layout.reset().root;\n" + "}\n" + "\n";
	}

}
