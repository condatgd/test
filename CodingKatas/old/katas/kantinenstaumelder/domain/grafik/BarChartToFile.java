package katas.kantinenstaumelder.domain.grafik;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;

public class BarChartToFile {
    public static void main(String[] args) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        // TODO: Populate dataset with data from KantinenStatistikSequenz
        dataset.addValue((Number)10, "In", "i1");
        dataset.addValue((Number)20, "Out", "i1");
        dataset.addValue((Number)7, "Other", "i1");
        dataset.addValue((Number)11, "In", "i2");
        dataset.addValue((Number)22, "Out", "i2");
        dataset.addValue((Number)5, "Other", "i2");

        JFreeChart barChart = ChartFactory.createBarChart(
                // String title, String categoryAxisLabel, String valueAxisLabel, CategoryDataset dataset,
                // PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls
                "Chart Title","Zeitraum", "Anzahl",
                dataset, PlotOrientation.VERTICAL, true, false, false);

        try {
            // Save chart as JPEG to specified file
            ChartUtilities.saveChartAsJPEG(new File("BarChart.jpeg"), barChart, 500, 500);
            System.out.println("Chart saved successfully!");
        } catch (Exception e) {
            System.err.println("Problem occurred creating chart.");
        }
    }
}