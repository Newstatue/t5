package nz.ac.massey.cs.sdc.parsers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class RssFeedReader {
    public static void main(String[] args) throws Exception {
        // 读取 XML 文件
        File file = new File("media-technology.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Rss.class);

        // 创建 Unmarshaller 并解组 XML
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Rss rss = (Rss) unmarshaller.unmarshal(file);

        // 获取 RSS 项目列表
        List<RssItem> items = rss.getChannel().getItem();

        // 遍历每个 RSS 项目并输出信息
        for (RssItem item : items) {
            // 遍历 titleOrDescriptionOrLink 列表
            for (Object obj : item.getTitleOrDescriptionOrLink()) {
                if (obj instanceof JAXBElement<?> element) {
                    String elementName = element.getName().getLocalPart();

                    // 通过 elementName 判断是否为 title, description 或 link
                    switch (elementName) {
                        case "title":
                            System.out.println("Title: " + element.getValue());
                            break;
                        case "description":
                            System.out.println("Description: " + element.getValue());
                            break;
                        case "link":
                            System.out.println("Link: " + element.getValue());
                            break;
                        default:
                            break;
                    }
                }
            }
            System.out.println();
        }
    }
}
