import os
from glob import glob
import pyan
from IPython.display import HTML


class StaticalCallGraph:
    def __init__(self, project_path):
        self.project_path = project_path

    def make_call_graph(self):
        files = [fn for fn in glob(self.project_path + "/**/*.py", recursive=True)
                 if 'venv' not in os.path.normpath(fn)]

        files_without_tests = []
        for file in files:
            file_name = file[file.find(self.project_path) + len(self.project_path):]
            if "test" not in file_name:
                files_without_tests.append(file)

        call_graph = HTML(pyan.create_callgraph(filenames=files_without_tests, format='html'))

        destination = self.project_path + "/static_call_graph.html"

        call_graph_file = open(destination, 'w')
        message = call_graph.data
        call_graph_file.write(message)
        call_graph_file.close()