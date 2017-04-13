package uk.ac.ucl.cs.radar.utilities;


import java.util.Arrays;

public class Statistics {
	double[] data;
    int size;   
    public Statistics(double[] data) 
    {
        this.data = data;
        size = data.length;
    }
    
    public double computeMean()
    {
        double sum =  0.0;
        for(double a : data)
            sum += a;
        return  sum/size;
    }
    public double computeVariance()
    {
        Double mean = computeMean();
        Double temp = 0.0;
        for(double a :data)
            temp += (mean-a)*(mean-a);
        return  temp/size;
    }



    public double computeStdDev()
    {
        return  Math.sqrt(computeVariance());
    }

    public double computeMedian() 
    {
       Arrays.sort(data);

       if (data.length % 2 == 0) 
       {
          return  ((data[(data.length / 2) - 1]) + (data[data.length / 2])) / 2.0;
       } 
       else 
       {
          return data[data.length / 2];
       }
    }
   

}
