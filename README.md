# RADAR
Requirements and Architecture Decision AnalyserR

RADAR is a lightweight modelling language and decision analysis tool to support multi-objective decision under uncertainty in software requirements engineering and architectural design. RADAR analysis provides  useful feedback to requirement engineers and software designers about:

  - Which decisions are better than others in their model.
  - What objective values can be attained with with different designs.
  - What tradeoffs can be made between shortlisted solutions.
  - What parameter uncertainty may deserve additional data collection and analysis before making their decision.
  - What parameter uncertainty does not matter to their decision.

You can find details about RADAR on the [project webpage](http://www0.cs.ucl.ac.uk/staff/S.Busari/RADAR/)
# Paper
The modelling language and decision analysis support are described in the  [paper](http://www0.cs.ucl.ac.uk/staff/S.Busari/RADAR/radar_icse17.pdf) under submission.

# Instructions on running the souce code
The tool is implemented in Java and uses [ANTLR](http://www.antlr.org/) to generate model parser, generates diagrams in DOT format, and uses [Graphviz ](http://graphviz.org/)  to visualise the diagrams. To continue development on RADAR, follow the instructions below:

  - Clone or download the project source code from the GIT repository
  - Import the project into your IDE of preference e.g. Eclipse Luna.
  - Add all the libraries listed below to the project build path.
  - Build the project 
  - Right click on the file "RADAR_GUI.java" and click "Run AS" to run java application.
  
The following are the libraries to be added to your project. They can be found within the downloaded project in the folder "uk.ac.ucl.cs.lib" :
  - antlr-4.5.1-complete.jar, which is used to generate model parser. 
  - commons-lang3-3.4.jar, which contains some string manipulating functions
  - commons-math3-3.6.1.jar, which containes some of the maths functions used for statistical and probabilistc analysis.
  - jcommander-1.7.jar, used to build the command line tool
  - opencsv-3.1.jar, for managing  csv files.
  - jfreechart-1.0.19.lar and jcommon-1.0.23.jar for generating 2D plots 
  - orsoncharts-1.5.jar for generating 3D plots.
