#FantaGraph
Multi-Modules Maven Project

- mvn clean package
- cd fantaGraphDB
- mvn exec:java -Dexec.mainClass="graph.DashboardBuilder"
- cd ..
- copy fantaGraphUtility-1.0.jar in fantaGraphUtility/target folder
- paste in fantaGraphAPI/dependencies
- cd fantaGraphAPI
- mvn validate
- mvn clean install
- mvn spring-boot:run