'''
Created on 26 avr. 2010

@author: mathieuacher
'''

from pygments import highlight
from pygments.lexers.agile import PythonLexer
from pygments.formatters.img import ImageFormatter
from pygments.formatters.svg import SvgFormatter
from pygments.formatters.latex import LatexFormatter
from pygments.formatters.html import HtmlFormatter
from pygments.token import Name, Keyword
from pygments.scanner import Scanner
from pygments.lexer import Lexer, RegexLexer, include, bygroups, using, \
                           this, combined
from pygments.util import get_bool_opt, get_list_opt
from pygments.token import \
     Text, Comment, Operator, Keyword, Name, String, Number, Punctuation, \
     Error

import os.path
import sys

class FamiliarLexer(PythonLexer):
    
    
     
    EXTRA_KEYWORDS = ['slice', 'including', 'cores', 'serialize', 'constraint', 'constraints', 'removeConstraint', 'addConstraint', 'deads', 'setUnion', 'setIntersection', 'to', 'falseOptionals', 'excluding', 'constraints', 'withMapping', 'map', 'cleanup', 'Set', 'asFM', 'addConstraint', 'aggregate', 'isConflicting', 'autoSelect', 'setIsEmpty', 'isComplete', 'isNull', 'isExisting', 'diff', 'counting', 'removeFeature', 'renameFeature', 'isValid', 'requires', 
                      'implies', 'excludes', 'FeatureModel', 'Feature', 'String', 
                      'operator', 'configuration', 'select', 'deselect', 'size', 'unselect',
                      'min', 'max', 'random', 'not', '==', 'else', 'configs', 'set', 'setAdd', 
                      'exit', 'extract', 'setEmpty', 'size', 'export', 'hide', 'strConcat',
                      'renameFeature', 'and', 'or', 'mand', 'opt', 'xor', 'or', 'str_concat', 'eq', 'neq', 'do', 
                      'neq', 'merge', 'union', 'sunion', 'intersection', 'whichfm', 'name', 'run', 
                      'into', 'valid?', 'hide', 'parent', 'removeFeature', 'parameter', 'then', 'if', 
                      'end', 'is_null', 'root', 'with', 'print_var', 'print', 'children', 'foreach', 'in', 
                      'selectedF', 'deselectedF', 'unselectedF', 'removeVariable',
                      'println', 'copy', 'into', 'FM', 'as', 'assert', 'size', 'insert', 'compare']

    def get_tokens_unprocessed(self, text):
        for index, token, value in PythonLexer.get_tokens_unprocessed(self, text):
            if token is Name and value in self.EXTRA_KEYWORDS:
                yield index, Keyword.Declaration, value
            else:
                yield index, token, value
    
    tokens = {
        'root': [
            # method names
            (r'^(\s*(?:[a-zA-Z_][a-zA-Z0-9_\.\[\]]*\s+)+?)' # return arguments
             r'([a-zA-Z_][a-zA-Z0-9_]*)'                    # method name
             r'(\s*)(\()',                                  # signature start
             bygroups(using(this), Name.Function, Text, Operator)),
            (r'[^\S\n]+', Text),
            (r'//.*?\n', Comment.Single),
            (r'/\*.*?\*/', Comment.Multiline),
            (r'@[a-zA-Z_][a-zA-Z0-9_\.]*', Name.Decorator),
            (r'(assert|break|case|catch|continue|default|do|else|finally|for|'
             r'if|goto|instanceof|new|return|switch|this|throw|try|while)\b',
             Keyword),
            (r'(abstract|const|enum|extends|final|implements|native|private|'
             r'protected|public|static|strictfp|super|synchronized|throws|'
             r'transient|volatile)\b', Keyword.Declaration),
            (r'(boolean|byte|char|double|float|int|long|short|void)\b',
             Keyword.Type),
            (r'(package)(\s+)', bygroups(Keyword.Namespace, Text)),
            (r'(true|false|null)\b', Keyword.Constant),
            (r'(class|interface)(\s+)', bygroups(Keyword.Declaration, Text), 'class'),
            (r'(import)(\s+)', bygroups(Keyword.Namespace, Text), 'import'),
            (r'"(\\\\|\\"|[^"])*"', String),
            (r"'\\.'|'[^\\]'|'\\u[0-9a-f]{4}'", String.Char),
            (r'[a-zA-Z_][a-zA-Z0-9_]*:', Name.Label),
            (r'[a-zA-Z_\$][a-zA-Z0-9_]*', Name),
            (r'[~\^\*!%&\[\]\(\)\{\}<>\|+=:;,./?-]', Operator),
            (r'[0-9][0-9]*\.[0-9]+([eE][0-9]+)?[fd]?', Number.Float),
            (r'0x[0-9a-f]+', Number.Hex),
            (r'[0-9]+L?', Number.Integer),
            (r'\n', Text)
        ],
        'class': [
            (r'[a-zA-Z_][a-zA-Z0-9_]*', Name.Class, '#pop')
        ],
        'import': [
            (r'[a-zA-Z0-9_.]+\*?', Name.Namespace, '#pop')
        ],
    }  
        
   


# constants
# PATH_FOLDER = "/Users/mathieuacher/Desktop/WorkspaceOpenEmbedd/FMScript/examples/" # path folder in which files are located
#PATH_FOLDER = "/Users/mathieuacher/Desktop/runtime-FOO/FMM/" # path folder in which files are located
#PATH_FOLDER = "/Users/mathieuacher/Desktop/runtime-FOO/" # path folder in which files are located
#PATH_FOLDER = "/Users/mathieuacher/Documents/workspaceScala/slicingfms/"
#PATH_FOLDER = "/Users/mathieuacher/Desktop/PhD/RESEARCHINPROGRESS/ArchFM/FML/"
#PATH_FOLDER = "/Users/mathieuacher/Desktop/PhD/RESEARCHINPROGRESS/SCP-FAMILIAR/FML/"
#PATH_FOLDER = "/Users/macher/Documents/runtime-EclipseApplication/FraSCAtiSoSyM/"
PATH_FOLDER = "/Users/macher1/Documents/FML-scripts/"
PATH_OUPUT = "" # where to serialize the output


# proceed arguments
if (not len(sys.argv) >= 2): 
    sys.exit("1 or 2 arguments are required")

aFile = sys.argv[1]

# by default, the formatter is HTML
formatter = HtmlFormatter(linenos=False, cssclass="code", style="trac")
aFormat = 'html'


if (len(sys.argv) == 3): 
    aFormat = sys.argv[2]
    if (aFormat == 'tex'):
        print "Latex!"
        formatter = LatexFormatter(linenos=True, cssclass="highlight", style="borland", full=True)
    if (aFormat == 'png'):
        formatter = ImageFormatter(line_numbers=True, cssclass="highlight", style="borland")
    if (aFormat == 'svg'):
        formatter = SvgFormatter(line_numbers=False, cssclass="highlight", style="borland")  
else:
    print "No format specified. By default: " + aFormat

filename =  PATH_FOLDER + aFile + ".fml" #".fmm"
outputf = open(PATH_OUPUT + os.path.basename(aFile) + "." + aFormat, "w")
filin = open(filename, 'r')
foo_code = filin.read()
lexer = FamiliarLexer()
#formatter = HtmlFormatter(linenos=True, cssclass="highlight", style="trac")
#formatter = SvgFormatter(line_numbers=False, cssclass="highlight", style="borland")
#formatter = ImageFormatter(line_numbers=False, cssclass="highlight", style="borland")
#HtmlFormatter(linenos=True, cssclass="highlight", style="emacs", full=True)
#style=emacs,trac, colorful, borland

result = highlight(foo_code, lexer, formatter, outputf)

#print sys.argv[1]

#print result


