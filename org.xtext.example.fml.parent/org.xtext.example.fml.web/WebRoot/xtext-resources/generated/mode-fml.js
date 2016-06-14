define(["ace/lib/oop", "ace/mode/text", "ace/mode/text_highlight_rules"], function(oop, mText, mTextHighlightRules) {
	var HighlightRules = function() {
		var keywords = "ARBITRARY|Boolean|Configuration|Constraint|DIMACS|Double|FM|Feature|FeatureModel|GENERALIZATION|Integer|MAX|MIN|Mutex|Or|RANDOM|REFACTORING|S2T2|SPECIALIZATION|SPLOT|Set|String|TVL|Xor|addConstraint|aggregate|aggregateMerge|ancestors|as|asFM|assert|autoSelect|bdd|children|cleanup|cliques|compare|computeBiimplies|computeExcludes|computeImplies|computeMUTEXGroups|computeORGroups|computeXORGroups|configs|configuration|constraint|constraints|convert|copy|cores|counting|cp|cpu|crossproduct|ctcr|deads|dependencies|descendants|deselect|deselectedF|diff|do|else|end|eq|excluding|exit|export|extract|false|falseOptionals|fd|featureide|featuremodel|features|fmcalc|fml|fmlbdd|fmlconstraints|foreach|fullMandatorys|gdisplay|getBiimpliesConstraint|getBiimpliesHierarchy|getExcludesConstraint|getExcludesHierarchy|getImpliesConstraint|getImpliesHierarchy|getMUTEXGroups|getORGroups|getXORGroups|glisting|gls|hide|hierarchy|if|in|including|insert|intersection|into|isComplete|isConflicting|isExisting|isNull|isValid|ksynthesis|leaves|ls|mand|map|memory|merge|mtxGroup|name|names|nbFeatures|neq|not|operator|opt|orGroup|over|parameter|parent|print|println|pw|quit|removeConstraint|removeFeature|removeVariable|renameFeature|rm|root|run|save|select|selectedF|serialize|setAdd|setAlternative|setBelongs|setDiff|setEmpty|setIntersection|setIsEmpty|setMandatory|setOptional|setOr|setRemove|setUnion|sibling|size|slice|strConcat|strIndexOf|strInit|strLength|strSubstring|sunion|then|to|true|union|unmap|unselect|unselectedF|vars|whichfm|with|withMapping|xmi|xorGroup";
		this.$rules = {
			"start": [
				{token: "comment", regex: "\\/\\/.*$"},
				{token: "comment", regex: "\\/\\*", next : "comment"},
				{token: "string", regex: '["](?:(?:\\\\.)|(?:[^"\\\\]))*?["]'},
				{token: "string", regex: "['](?:(?:\\\\.)|(?:[^'\\\\]))*?[']"},
				{token: "constant.numeric", regex: "[+-]?\\d+(?:(?:\\.\\d*)?(?:[eE][+-]?\\d+)?)?\\b"},
				{token: "keyword", regex: "\\b(?:" + keywords + ")\\b"}
			],
			"comment": [
				{token: "comment", regex: ".*?\\*\\/", next : "start"},
				{token: "comment", regex: ".+"}
			]
		};
	};
	oop.inherits(HighlightRules, mTextHighlightRules.TextHighlightRules);
	
	var Mode = function() {
		this.HighlightRules = HighlightRules;
	};
	oop.inherits(Mode, mText.Mode);
	Mode.prototype.$id = "xtext/fml";
	Mode.prototype.getCompletions = function(state, session, pos, prefix) {
		return [];
	}
	
	return {
		Mode: Mode
	};
});
