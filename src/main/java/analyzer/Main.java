package analyzer;

import analyzer.analyze.Result;
import analyzer.analyze.ResultsArray;
import web.AnalyzerController;

import java.util.List;


import static analyzer.analyze.Controller.getResults;

public class Main {
    public static void main(String args[]){
        final List<ResultsArray> results = getResults();

    }


}
