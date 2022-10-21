import plotly.express as px
import sys
import json
import os

class SunBurstVisualization:
    def __init__(self, project_path):

        f = open(project_path + os.path.sep + "results.json")

        self.result = json.load(f)

        def createsunburst(self, result):
            self.result=data

        character_ = []
        parent_ = []
        values_ = []
        act_parent = None

        for files in self.result["files"]:

            for k,v in files.items():
                if k == "relativePath":
                    character_.append(v)
                    parent_.append("")
                    values_.append(0)
                    act_parent = v

                if isinstance(v, list):
                    classes = v
                    for listaelem_class in classes:
                        if listaelem_class["name"] == "":
                            for metodus_elem in listaelem_class["methods"]:
                                if metodus_elem["name"] == "":
                                    character_.append(files["relativePath"]+("\\")+listaelem_class["name"]+("\\")+("None_method"))
                                else:
                                    character_.append(files["relativePath"]+("\\")+listaelem_class["name"]+("\\")+metodus_elem["name"])

                                parent_.append(act_parent)
                                values_.append(metodus_elem["och"])
                                act_metodus_parent=character_[-1]

                                for statement_elem in metodus_elem["statements"]:
                                    character_.append(
                                    files["relativePath"] + ("\\") + listaelem_class["name"] + ("\\") + metodus_elem[
                                        "name"]+ ("\\") + statement_elem["line"])

                                    parent_.append(act_metodus_parent)
                                    values_.append(statement_elem["och"])

                        else:
                            character_.append(files["relativePath"]+("\\")+listaelem_class["name"])
                            akt_class_parent = character_[-1]
                            parent_.append(act_parent)
                            values_.append(listaelem_class["och"])

                            for metodus_elem in listaelem_class["methods"]:
                                if metodus_elem["name"] == "":
                                    character_.append(files["relativePath"]+("\\")+listaelem_class["name"]+("\\")+("None_method"))
                                else:
                                    character_.append(files["relativePath"]+("\\")+listaelem_class["name"]+("\\")+metodus_elem["name"])

                                parent_.append(akt_class_parent)
                                values_.append(metodus_elem["och"])
                                act_metodus_parent = character_[-1]


                                for statement_elem in metodus_elem["statements"]:
                                    character_.append(
                                files["relativePath"] + ("\\") + listaelem_class["name"] + ("\\") + metodus_elem[
                                    "name"] + ("\\") + statement_elem["line"])

                                    parent_.append(act_metodus_parent)
                                    values_.append(statement_elem["och"])

        data = dict(
            character=character_,
            parent=parent_,
            value=values_
            )

        #[print(v) for k,v in data.items()]

        fig = px.sunburst(
            data,
            names='character',
            parents='parent',
            values='value',
            )
#fig.show()
        fig.write_html("sunburst.html")

        sys.exit()




