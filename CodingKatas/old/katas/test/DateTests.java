package katas.test;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

public class DateTests {

    static Map<String, List<List<SubNodeListGetter>>> subNodesListConfig = Map.of(
            "zdf-nt:page-index", List.of(
                    List.of(new ChildsByNameAndNodeType("zdf:stage",""), new ChildsByNameAndNodeType("sophora-extension:teaser", "")),
                    List.of(new ChildsByNameAndNodeType("", "zdf-nt:group-fragment-homepage"), new ChildsByNameAndNodeType("sophora-extension:teaser", "")),
                    List.of(new ChildsByNameAndNodeType("", "zdf-nt:group-fragment-indexpage"), new ChildsByNameAndNodeType("sophora-extension:teaser", ""))
            )
    );

    @Data
    @AllArgsConstructor
    static class OO {
        private Integer date;
    }

    public static void main(String[] args) {
        List<OO> liste = List.of(new OO(1), new OO(null));
        Integer max = Collections.max(liste.stream()
                .map(OO::getDate)
                .filter(Objects::nonNull)
                .toList());
        System.out.println(max);
    }

    public static void main1(String[] args) {

        INode s11 = new INode("sophora-extension:teaser", "", 455, List.of());
        INode s12 = new INode("sophora-extension:teaser", "", 6, List.of());

        INode s21 = new INode("sophora-extension:teaser", "", 3, List.of());
        INode s22 = new INode("sophora-extension:teaser", "", 77, List.of());

        INode s1 = new INode("zdf:stage", "", 2, List.of(s11,s12));
        INode s2 = new INode("xx", "zdf-nt:group-fragment-indexpage", 34, List.of(s21,s22));

        INode doc = new INode("top", "zdf-nt:page-index", 3, List.of(s1,s2));

        int newestDate = findNewestDateAll(doc, doc.getDate(), subNodesListConfig.get(doc.getType()));

        System.out.println(newestDate);

    }


    final List<String> PRIMARY_TYPES_TO_HANDLE =
            Arrays.asList(
                    "zdf-nt:page-video",
                    "zdf-nt:special-page-search",
                    "zdf-nt:page-picture-gallery",
                    "zdf-nt:special-page-sitemap",
                    "zdf-nt:special-page-broadcast-missed",
                    "zdf-nt:special-page-brand-a-z",
                    "zdf-nt:special-page-my-zdf",
                    "zdf-nt:special-page-live-tv",
                    "zdf-nt:page-home",
                    "zdf-nt:fragment-homepage",
                    "zdf-nt:page-index",
                    "zdf-nt:fragment-indexpage"
                    );

    interface SubNodeListGetter {
        List<INode> getNodes(INode document);
    }

    static class ChildsByNameAndNodeType  implements SubNodeListGetter {
        private final String name;
        private final String nodeType;
        ChildsByNameAndNodeType(String name, String nodeType) {
            this.name = name;
            this.nodeType = nodeType;
        }
        public List<INode> getNodes(INode document) {
            boolean filterByName = this.name !=null && !this.name.isBlank();
            boolean filterByType = this.nodeType !=null && !this.nodeType.isBlank();

            if(filterByName && filterByType) {
                List<INode> nodes = document.getNodesByName(name);
                return nodes.stream()
                        .filter(node -> node.getPrimaryType().equals(nodeType))
                        .collect(Collectors.toList());
            }
            if(filterByName) {
                return document.getNodesByName(this.name);
            }
            if(filterByType) {
                return document.getNodesByType(nodeType);
            }
            return List.of();
        }
    }

    static int findNewestDateAll(INode document, int docLastModified, List<List<SubNodeListGetter>> subNodeListGetterListList) {
        return Collections.max(subNodeListGetterListList.stream()
                .map(list -> findNewestDate(document, docLastModified, list) )
                .collect(Collectors.toList()));
    }

    static int findNewestDate(INode document, int docLastModified, List<SubNodeListGetter> subNodeListGetterList) {
        if(!subNodeListGetterList.isEmpty()) {
            SubNodeListGetter subNodeListGetter = subNodeListGetterList.get(0);
            List<INode> nodes = subNodeListGetter.getNodes(document);
            List<Integer> newestSubDates = nodes.stream()
                    .map(node ->
                            findNewestDate(node, node.getDate("sophora:publicationDate"), subNodeListGetterList.subList(1, subNodeListGetterList.size()))
                    )
                    .toList();
            if(!newestSubDates.isEmpty()) {
                Integer maxSubDate = Collections.max(newestSubDates);
                return Collections.max(List.of(docLastModified, maxSubDate));
            }
        }
        return docLastModified;
    }



}
