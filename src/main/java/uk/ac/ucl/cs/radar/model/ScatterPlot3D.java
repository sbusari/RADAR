/* ===================
 * Orson Charts - Demo
 * ===================
 * 
 * Copyright (c) 2013-2016, Object Refinery Limited.
 * All rights reserved.
 *
 * http://www.object-refinery.com/orsoncharts/index.html
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *   - Neither the name of the Object Refinery Limited nor the
 *     names of its contributors may be used to endorse or promote products
 *     derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL OBJECT REFINERY LIMITED BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * Note that the above terms apply to the demo source only, and not the 
 * Orson Charts library.
 * 
 */

package uk.ac.ucl.cs.radar.model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import uk.ac.ucl.cs.radar.exception.CyclicDependencyException;
import uk.ac.ucl.cs.radar.utilities.Helper;

import com.orsoncharts.Chart3D;
import com.orsoncharts.Chart3DFactory;
import com.orsoncharts.Chart3DPanel;
import com.orsoncharts.Colors;
import com.orsoncharts.data.xyz.XYZDataset;
import com.orsoncharts.data.xyz.XYZSeries;
import com.orsoncharts.data.xyz.XYZSeriesCollection;
import com.orsoncharts.graphics3d.Dimension3D;
import com.orsoncharts.graphics3d.ViewPoint3D;
import com.orsoncharts.label.StandardXYZLabelGenerator;
import com.orsoncharts.plot.XYZPlot;
import com.orsoncharts.renderer.xyz.ScatterXYZRenderer;

/**
 * Scatter plot demo chart configuration.
 */
public class ScatterPlot3D extends JFrame {

    /**
     * Creates a scatter chart based on the supplied dataset.
     * 
     * @param dataset  the dataset.
     * @param semanticModel parsed decision model.
     * @return A scatter chart. 
     */
    public Chart3D createChart(Model semanticModel, XYZDataset dataset) {
    	
    	String chartTitle =   "Pareto front obtained for " + semanticModel.getModelName() + " model." ;
		String Xlabel =   semanticModel.getObjectives().get(0).getLabel();
		String Ylabel =   semanticModel.getObjectives().get(1).getLabel();
		String Zlabel =   semanticModel.getObjectives().get(2).getLabel();
		

    	
        Chart3D chart = Chart3DFactory.createScatterChart("", "", dataset, Xlabel, Ylabel, Zlabel);
        XYZPlot plot = (XYZPlot) chart.getPlot();
        plot.setDimensions(new Dimension3D(10.0, 5, 10));
        
        plot.setLegendLabelGenerator(new StandardXYZLabelGenerator(
                StandardXYZLabelGenerator.COUNT_TEMPLATE));
        ScatterXYZRenderer renderer = (ScatterXYZRenderer) plot.getRenderer();
        renderer.setSize(0.15);
        renderer.setColors(new Color(255, 128, 128), new Color(128, 255, 128));
        chart.setViewPoint(ViewPoint3D.createAboveRightViewPoint(40));
        return chart;
    }
    
    /**
     * Creates a sample dataset (hard-coded for the purpose of keeping the
     * demo self-contained - in practice you would normally read your data
     * from a file, database or other source).
     * @param analysis_result results of analysis.
     * @return A sample dataset.
     */
    public XYZDataset createDataset(AnalysisResult analysis_result) {
        XYZSeries s1 = createNonDominatedSeries("Non Dominated Solutions", analysis_result);
        XYZSeries s2 = createDominatedSeries("Dominated Solutions", analysis_result);
        XYZSeriesCollection dataset = new XYZSeriesCollection();
        dataset.add(s2);
        dataset.add(s1);
        return dataset;
    }
    
    private XYZSeries createNonDominatedSeries(String name,AnalysisResult analysis_result) {
    	float x_nonDom;
        float y_nonDom;
        float z_nonDom;
        float a_nonDom;
        
        
        XYZSeries s = new XYZSeries(name);

        for (int i =0; i < analysis_result.getShortListObjectives().size(); i ++){
        	x_nonDom = (float) (analysis_result.getShortListObjectives().get(i)[0]);
        	y_nonDom = (float) (analysis_result.getShortListObjectives().get(i)[1]);
        	z_nonDom = (float) (analysis_result.getShortListObjectives().get(i)[1]);
        	a_nonDom = (float)Math.random();
            s.add(x_nonDom, y_nonDom, z_nonDom);
		}
        return s;
    }
    private  XYZSeries createDominatedSeries(String name,AnalysisResult analysis_result) {
    	float x_dom;
        float y_dom;
        float z_dom;
        float a_dom;
    	XYZSeries s = new XYZSeries(name);
        for (int i =0; i < analysis_result.getEvaluatedObjectives().size(); i ++){
        	x_dom = (float) (analysis_result.getEvaluatedObjectives().get(i)[0]);
        	y_dom = (float) (analysis_result.getEvaluatedObjectives().get(i)[1]);
        	z_dom = (float) (analysis_result.getEvaluatedObjectives().get(i)[1]);
        	a_dom = (float)Math.random();
            s.add(x_dom, y_dom, z_dom);
		}
        return s;
    }
    /**
     *Plots the scatter plot
     * @param semanticModel semantic model obtained from parsing.
     * @param outputpath output directory where the results is stored.
     * @param analysisResult results of model analysis.
     * @throws IOException when file not found.
     */
    public void plot (Model semanticModel, String outputpath, AnalysisResult analysisResult) throws IOException{

		Chart3D chart =createChart(semanticModel, createDataset(analysisResult));
		Chart3DPanel chartPanel = new Chart3DPanel(chart) ;
        chartPanel.setPreferredSize(new Dimension(700, 500));
        this.add(chartPanel, BorderLayout.CENTER);
        String frameTilte = "Pareto front obtained  for " + semanticModel.getModelName() + " model." ;
        this.setTitle(frameTilte);
        this.pack();
        this.setLocationRelativeTo(null);
        
        this.setVisible(true);
        
        Helper.writeImageToFolder(outputpath  + "Figure/", Helper.getImage(this), "PNG", semanticModel.getModelName());
    }
    
}
