package uk.ac.ucl.cs.radar.userinterface;
/*
 * Copyright 2006-2008 Kees de Kooter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.PlainDocument;
import javax.swing.text.PlainView;
import javax.swing.text.Segment;
import javax.swing.text.Utilities;

/**
 * Thanks: http://groups.google.com/group/de.comp.lang.java/msg/2bbeb016abad270
 *
 * IMPORTANT NOTE: regex should contain 1 group.
 *
 * Using PlainView here because we don't want line wrapping to occur.
 *
 * @author kees
 * @date 13-jan-2006
 *
 */

/**
 * Improved and modified by
 * @author INTEGRALSABIOLA
 *
 */
public class ModelView extends PlainView {

	private static String DISTRIBUTIONS = "triangular mormal normalCI geometric deterministic exponential binomial uniform random";
    private static HashMap<Pattern, Color> patternColors;
    //private static String OPERATORS =  "[\\+\\-\\/\\*&&\\|<>=!]?";
    private static String RADAR_TEXT = "[0-9\\(\\)]?[A-Za-z ]+[A-Za-z0-9\\-_ \\(\\)]*(:[A-Za-z ]+[A-Za-z0-9\\-_\\(\\) ]+)?";
    private static String RADAR_MODEL = "(\\bModel\\b)";
    private static String RADAR_NUMBER = "(\\b[0-9]+\\.?([0-9]+)?\\b)";
    private static String RADAR_OBJECTIVE = "(\\bObjective\\b)";
    private static String RADAR_MAXIMISE = "(\\bMax\\b)";
    private static String RADAR_MINIMISE = "(\\bMin\\b)";
    private static String RADAR_EV = "(\\bEV\\b)";
    private static String RADAR_PR = "(\\bPr\\b)";
    private static String RADAR_PERCENTILE = "(\\bpercentile\\b)";
    private static String RADAR_DECISION ="(\\bdecision\\b)"; //(\\w*^triangular$\\w*)
    private static String RADAR_COMMENT = "(\\/\\/[\\w .:_,!/+^*&~.();=]*|\\/\\*[\\w .:_,!/+^*&~.();=]*\\*\\/)";
    //private static String RADAR_COMMENT = "(\\/\\/(?!\\w*\\bnormal\\b\\w*|\\w*\\btriangular\\b\\w*).*)";
    //private static String RADAR_COMMENT = "(\\/\\/[\\w :_,!/+^*&~.();=]*|\\/\\*[\\w :_,!/+^*&~.();=](?!\\w*\\bnormal\\b\\w*|\\w*\\btriangular\\b\\w*)*\\*\\/)";
    //private static String RADAR_COMMENT = "(\\/\\/[\\w :_,!/+^*&~.();=]*|\\/\\*[\\w :_,!/+^*&~.();=]*\\*\\/)";// here
    //private static String RADAR_COMMENT = "(\\/\\/[\\w :_,!/+^*&~.();=" + DISTRIBUTIONS +"]*|\\/\\*[\\w :_,!/+^*&~.();=" +DISTRIBUTIONS +"]*\\*\\/)";
    //private static String RADAR_COMMENT = "(\\/\\/ [\\w ]*?|\\/\\* [\\w ]*? \\*\\/)";
    //private static String RADAR_OPTION = "(" + "\""+ RADAR_TEXT + "\"" + ")";
    //private static String RADAR_DISTRIBUTION = "(\\w*\\btriangular\\b\\w*|\\w*normalCI\\w*|\\w*normal\\w*|\\w*geometric\\w*|\\w*deterministic\\w*|\\w*exponential\\w*|\\w*binomial\\w*|\\w*uniform\\w*|\\w*random\\w* )";
    private static String RADAR_DISTRIBUTION = "(\\w*\\btriangular\\b\\w*|\\w*\\bnormalCI\\b\\w*|\\w*\\bnormal\\b\\w*|\\w*\\bgeometric\\b\\w*|\\w*\\bdeterministic\\b\\w*|\\w*\\bexponential\\b\\w*|\\w*\\bbinomial\\b\\w*|\\w*\\buniform\\b\\w*|\\w*\\brandom\\b\\w* )";
   // private static String RADAR_OPERATORS = "(\\s*\\+\\s*|\\s*\\-\\s*|\\s*\\/\\s*|\\s*\\*\\s*|\\s*&&\\s*|\\s*\\|\\|\\s*|\\s*<\\s*|\\s*>\\s*|\\s*<=\\s*|\\s*>=\\s*|\\s*!\\s*)";
    //private static String RADAR_OPERATORS = "(" +OPERATORS +")";

    
    static {
        // NOTE: the order is important!
        patternColors = new LinkedHashMap<Pattern, Color>();
        
        patternColors.put(Pattern.compile(RADAR_MODEL), Color.BLUE); //new Color(131,111,255)
        patternColors.put(Pattern.compile(RADAR_NUMBER), Color.RED);
        patternColors.put(Pattern.compile(RADAR_OBJECTIVE), Color.BLUE);
        patternColors.put(Pattern.compile(RADAR_MAXIMISE), Color.BLUE);
        patternColors.put(Pattern.compile(RADAR_MINIMISE), Color.BLUE);
        patternColors.put(Pattern.compile(RADAR_EV),  Color.BLUE);
        patternColors.put(Pattern.compile(RADAR_PR), Color.BLUE);
        patternColors.put(Pattern.compile(RADAR_PERCENTILE), Color.BLUE);
        patternColors.put(Pattern.compile(RADAR_DECISION), Color.BLUE); //Color.BLUE, 30,144,255,  255,99,71
        //patternColors.put(Pattern.compile(RADAR_OPTION), new Color(99,184,255)); //72,118,255, 255,99,71
       
        patternColors.put(Pattern.compile(RADAR_COMMENT), new Color(124,252,0));//0,201,87
        patternColors.put(Pattern.compile(RADAR_DISTRIBUTION), Color.BLUE); //255,99,71, 99,184,255
        //patternColors.put(Pattern.compile(RADAR_OPERATORS), Color.RED); //99,184,255
        //
    }

    public ModelView(Element element) {

        super(element);
        // Set tabsize to 4 (instead of the default 8)
        getDocument().putProperty(PlainDocument.tabSizeAttribute, 4);
    }

    @Override
    protected int drawUnselectedText(Graphics graphics, int x, int y, int p0,
            int p1) throws BadLocationException {

        Document doc = getDocument();
        String text = doc.getText(p0, p1 - p0);

        Segment segment = getLineBuffer();

        SortedMap<Integer, Integer> startMap = new TreeMap<Integer, Integer>();
        SortedMap<Integer, Color> colorMap = new TreeMap<Integer, Color>();

        // Match all regexes on this snippet, store positions
        for (Map.Entry<Pattern, Color> entry : patternColors.entrySet()) {

            Matcher matcher = entry.getKey().matcher(text);
            //System.out.println(text);
            // adding this part for the comment section, it w
            if (text.contains("//")||text.contains("/*")|| text.contains("*/")){
            	//System.out.println("true");
            	while (matcher.find()) {
            		startMap.put(matcher.start(1), matcher.end());
                    colorMap.put(matcher.start(1), new Color(124,252,0));
            	}
            }else{
            	//System.out.println("false");
            	while (matcher.find()) {
                    startMap.put(matcher.start(1), matcher.end());
                    colorMap.put(matcher.start(1), entry.getValue());
                }
            }
            
            
        }

        // TODO: check the map for overlapping parts

        int i = 0;

        // Colour the parts
        for (Map.Entry<Integer, Integer> entry : startMap.entrySet()) {
            int start = entry.getKey();
            int end = entry.getValue();

            if (i < start) {
                graphics.setColor(Color.black);
                doc.getText(p0 + i, start - i, segment);
                x = Utilities.drawTabbedText(segment, x, y, graphics, this, i);
            }

            graphics.setColor(colorMap.get(start));
            i = end;
            doc.getText(p0 + start, i - start, segment);
            x = Utilities.drawTabbedText(segment, x, y, graphics, this, start);
        }

        // Paint possible remaining text black
        if (i < text.length()) {
            graphics.setColor(Color.black);
            doc.getText(p0 + i, text.length() - i, segment);
            x = Utilities.drawTabbedText(segment, x, y, graphics, this, i);
        }

        return x;
    }

}

