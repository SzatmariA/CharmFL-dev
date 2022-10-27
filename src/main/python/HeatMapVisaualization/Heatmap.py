import plotly.express as px
import sys
import json
import os

class HeatMapVisualization:
    def __init__(self, project_path):

        f = open(project_path + os.path.sep + "spectre.json")

        self.spectre = json.load(f)

        def createheatmap(self, result):
            self.spectre=list1

        spectre={'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\example.py::9': {'ef': 2, 'ep': 2, 'nf': 6, 'np': 2}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\example.py::10': {'ef': 6, 'ep': 2, 'nf': 2, 'np': 2}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\example.py::38': {'ef': 2, 'ep': 1, 'nf': 6, 'np': 3}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\example.py::39': {'ef': 2, 'ep': 2, 'nf': 6, 'np': 2}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\example.py::42': {'ef': 2, 'ep': 2, 'nf': 6, 'np': 2}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\example.py::16': {'ef': 1, 'ep': 1, 'nf': 7, 'np': 3}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\example.py::12': {'ef': 4, 'ep': 0, 'nf': 4, 'np': 4}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\example.py::25': {'ef': 4, 'ep': 0, 'nf': 4, 'np': 4}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\example.py::27': {'ef': 4, 'ep': 0, 'nf': 4, 'np': 4}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\example.py::8': {'ef': 2, 'ep': 2, 'nf': 6, 'np': 2}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\example.py::34': {'ef': 2, 'ep': 2, 'nf': 6, 'np': 2}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\example.py::35': {'ef': 2, 'ep': 2, 'nf': 6, 'np': 2}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\example.py::15': {'ef': 1, 'ep': 1, 'nf': 7, 'np': 3}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\example.py::18': {'ef': 0, 'ep': 1, 'nf': 8, 'np': 3}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\example.py::19': {'ef': 0, 'ep': 1, 'nf': 8, 'np': 3}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\example.py::36': {'ef': 0, 'ep': 1, 'nf': 8, 'np': 3}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\example.py::11': {'ef': 2, 'ep': 0, 'nf': 6, 'np': 4}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\example.py::17': {'ef': 1, 'ep': 0, 'nf': 7, 'np': 4}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\directory\\example2.py::9': {'ef': 2, 'ep': 2, 'nf': 6, 'np': 2}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\directory\\example2.py::10': {'ef': 6, 'ep': 2, 'nf': 2, 'np': 2}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\directory\\example2.py::38': {'ef': 2, 'ep': 1, 'nf': 6, 'np': 3}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\directory\\example2.py::39': {'ef': 2, 'ep': 2, 'nf': 6, 'np': 2}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\directory\\example2.py::42': {'ef': 2, 'ep': 2, 'nf': 6, 'np': 2}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\directory\\example2.py::16': {'ef': 1, 'ep': 1, 'nf': 7, 'np': 3}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\directory\\example2.py::12': {'ef': 4, 'ep': 0, 'nf': 4, 'np': 4}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\directory\\example2.py::25': {'ef': 4, 'ep': 0, 'nf': 4, 'np': 4}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\directory\\example2.py::27': {'ef': 4, 'ep': 0, 'nf': 4, 'np': 4}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\directory\\example2.py::8': {'ef': 2, 'ep': 2, 'nf': 6, 'np': 2}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\directory\\example2.py::34': {'ef': 2, 'ep': 2, 'nf': 6, 'np': 2}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\directory\\example2.py::35': {'ef': 2, 'ep': 2, 'nf': 6, 'np': 2}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\directory\\example2.py::15': {'ef': 1, 'ep': 1, 'nf': 7, 'np': 3}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\directory\\example2.py::18': {'ef': 0, 'ep': 1, 'nf': 8, 'np': 3}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\directory\\example2.py::19': {'ef': 0, 'ep': 1, 'nf': 8, 'np': 3}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\directory\\example2.py::36': {'ef': 0, 'ep': 1, 'nf': 8, 'np': 3}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\directory\\example2.py::11': {'ef': 2, 'ep': 0, 'nf': 6, 'np': 4}, 'C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\directory\\example2.py::17': {'ef': 1, 'ep': 0, 'nf': 7, 'np': 4}}


        list1 = list()
        statement_statictics= list()
        list_statements=[]
        list_values=[]

        for k, v in spectre.items():
            if k.__contains__("directory"):
                key = k.rsplit('\\', 2)[-1]
                list_statements.append(key)
            else:
                key = k.rsplit('\\', 1)[-1]
                list_statements.append(key)

            print(k.rsplit('\\', 1)[-1], "value: ", v)#kijavitani
            for k2, v2 in v.items():
                print(k2, "value: ", v2)
                statement_statictics.append(v2)
            list1.append(statement_statictics)
            statement_statictics=list()
        fig=px.imshow(list1, title="Heatmap", x=["ef", "ep", "nf", "np"], y=list_statements, color_continuous_scale='Bluered_r')
        print(list1)
        print(len(list1))
        fig.write_html("heat.html")
        sys.exit()



        #fig.show()

