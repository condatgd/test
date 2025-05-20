package katas.gcode_tools;

import lombok.Data;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class CuraGCODETool {

    // https://www.3dpea.com/de/gcode-simulator

    public static void main(String[] args) {
        GCODEBlocks gcodeBlocks = parseGCODEBocks("PI3_inversion drehdingB.gcode");
        gcodeBlocks.reverseLayers();
        // System.out.println(gcodeBlocks);
        writeGCODEToFile(gcodeBlocks, "PI3_inversion drehdingB_layers_reversed.gcode");

    }

    private static void writeGCODEToFile(GCODEBlocks bocks, String filename) {
        try {
            PrintWriter writer = new PrintWriter(filename);
            for (String line : bocks.head) {
                writer.println(line);
            }
            for (List<String> layer : bocks.layers) {
                for (String line : layer) {
                    writer.println(line);
                }
            }
            for (String line : bocks.tail) {
                writer.println(line);
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static GCODEBlocks parseGCODEBocks(String filename) {
        GCODEBlocks blocks = new GCODEBlocks();
        try {
            List<String> allLines = Files.readAllLines(Paths.get(filename));
            int layer0Idx = allLines.indexOf(";LAYER:0");
            blocks.head = allLines.subList(0, layer0Idx);
            String sizeStr = blocks.head.get(blocks.head.size() - 1);
            String numLayersStr = sizeStr.substring(sizeStr.indexOf(":") + 1);
            int numLayers = Integer.parseInt(numLayersStr);
            blocks.layerCount = numLayers;
            List<String> rest = allLines.subList(layer0Idx, allLines.size());
            for(int l=1; l<numLayers; l++) {
                int layerlIdx = rest.indexOf(";LAYER:" + l);
                blocks.layers.add(new ArrayList<>(rest.subList(0, layerlIdx)));
                rest = rest.subList(layerlIdx, rest.size());
            }
            List<String> lastLayer = new ArrayList<>();
            boolean last = true;
            for(int i=0; i<rest.size(); i++) {
                String currentLine = rest.get(i);
                if(currentLine.startsWith(";TIME_ELAPSED:")) {
                    last = false;
                }
                if(last) {
                    lastLayer.add(currentLine);
                } else {
                    blocks.tail.add(currentLine);
                }
            }
            blocks.layers.add(lastLayer);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return blocks;
    }

    @Data
    public static class GCODEBlocks {
        private List<String> head;
        private List<String> tail = new ArrayList<>();
        private int layerCount;
        private List<List<String>> layers = new ArrayList<>();

        public void reverseLayers() {
            String maxZ = findMaxZ();
            Collections.reverse(layers);
            List<String> firstLayer = layers.get(0);
            if(!maxZ.isEmpty()) {
                firstLayer.add(1, "G0 Z" + maxZ);
            }
            for(int i=0; i<layers.size(); i++) {
                List<String> layer = layers.get(i);
                String removed = layer.remove(0);
                System.out.println("removed: " + removed);
                layer.add(0, ";LAYER:" + i);
            }
        }

        private String findMaxZ() {
            return head.stream()
                    .filter(s -> s.startsWith(";MAXZ:"))
                    .map(s -> s.substring(s.indexOf(":") + 1))
                    .findFirst()
                    .orElse("");
        }
    }


}
