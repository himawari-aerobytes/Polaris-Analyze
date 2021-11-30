package analyzer;

import analyzer.analyze.Result;
import web.AnalyzerController;

import java.util.List;
import java.util.Map;

import static analyzer.analyze.Controller.getResults;

public class Main {
    public static void main(String args[]){
        final List<Map<String, Result>> results = getResults();

    }


}
