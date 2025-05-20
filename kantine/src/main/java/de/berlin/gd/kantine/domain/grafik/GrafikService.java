package de.berlin.gd.kantine.domain.grafik;

import de.berlin.gd.kantine.domain.statistik.model.BerechnungTyp;
import de.berlin.gd.kantine.domain.ereignis.model.KantinenEreignisTyp;
import de.berlin.gd.kantine.domain.grafik.port.GrafikErstellung;
import de.berlin.gd.kantine.domain.statistik.model.KantinenStatistik;
import de.berlin.gd.kantine.domain.statistik.model.KantinenStatistikSequenz;
import org.apache.commons.io.IOUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
public class GrafikService implements GrafikErstellung {

    @Override
    public byte[] vollstaendigeStatistikAlsGrafik(KantinenStatistikSequenz kantinenStatistikSequenz) {
        DefaultCategoryDataset dataset = createDataset(kantinenStatistikSequenz);
        JFreeChart barChart = ChartFactory.createBarChart(
                "Chart Title","Zeitraum", "Anzahl",
                dataset, PlotOrientation.VERTICAL, true, false, false);
        try {
            File file = File.createTempFile("chart", "jpeg");
            String absolutePath = file.getAbsolutePath();
            ChartUtilities.saveChartAsJPEG(file, barChart, 500, 500);
            InputStream in = new FileInputStream(file);
            return IOUtils.toByteArray(in);
        } catch (Exception e) {
            System.err.println("Problem occurred creating chart.");
        }
        return null;
    }

    private DefaultCategoryDataset createDataset(KantinenStatistikSequenz kantinenStatistikSequenz) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<KantinenStatistik> statistiken = kantinenStatistikSequenz.getStatistiken();
        for (KantinenStatistik statistik : statistiken) {
            Map<KantinenEreignisTyp, Integer> kummulierteEreignisse = statistik.getKummulierteEreignisse();
            Map<BerechnungTyp, Integer> berechneteWerte = statistik.getBerechneteWerte();
            for (KantinenEreignisTyp ereignisTyp : kummulierteEreignisse.keySet()) {
                dataset.addValue(kummulierteEreignisse.get(ereignisTyp), ereignisTyp.toString(), statistik.getZeitraum().toString());
            }
            for (BerechnungTyp berechnungTyp : berechneteWerte.keySet()) {
                dataset.addValue(berechneteWerte.get(berechnungTyp), berechnungTyp.toString(), statistik.getZeitraum().toString());
            }
        }
        return dataset;
    }

}