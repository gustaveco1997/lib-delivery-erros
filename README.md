Referência:
https://gist.github.com/fernandezpablo85/03cf8b0cd2e7d8527063

Rodar commando para gerar dados do repositório da lib
mvn install:install-file -DgroupId="br.com.luminaapps" -DartifactId="lib-erros-api" -Dversion="1.0.0" -Dfile="C:\Users\gusta\Documents\projetos\entrega-app\delivery-erros-api\target\lib-erros-api-1.0.0.jar" -Dpackaging="jar" -DgeneratePom=true -DlocalRepositoryPath="./repository" -DcreateChecksum=true