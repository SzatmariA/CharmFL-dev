import os
import sys
from glob import glob
from call_graph_highlight import Highlighted_Callgraph
from statical_call_graph import StaticalCallGraph

project_path = sys.argv[1]
node = sys.argv[2]

call_graph = StaticalCallGraph(project_path)
call_graph.make_call_graph()

highlight_maker = Highlighted_Callgraph(project_path, node)
highlight_maker.add_highlighted_callgraph()