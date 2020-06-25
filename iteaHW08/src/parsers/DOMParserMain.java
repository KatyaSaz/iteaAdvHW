package parsers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMParserMain {
	
	private static boolean isFileValid(Document document) {

		NodeList ndlGroupId = document.getElementsByTagName("groupId");
		NodeList ndlArtifactId = document.getElementsByTagName("artifactId");
		NodeList ndlVersion = document.getElementsByTagName("version");
		
		for (int k = 0; k < ndlGroupId.getLength(); k++) {
			Node group = ndlGroupId.item(k);
			Node artifact = ndlArtifactId.item(k);
			Node version = ndlVersion.item(k);
			if(version==null||artifact==null||group==null) {
				System.out.println("Document isn't well formed");
				return false;
			}
		}
		
		for(int i=0; i<ndlVersion.getLength(); i++) {
			String [] numb = ndlVersion.item(i).getTextContent().split("\\.");
			for(int j=0; j<numb.length; j++) {
				try {
					Integer.valueOf(numb[j]);
				}catch (NumberFormatException e) {
					System.out.println("Field 'version' have to consist of numbers and dots.");
					return false;
				}
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("pom.xml"));

			if(!DOMParserMain.isFileValid(document)) {
				System.exit(1);
			}

			List<Dependency> dependences = new ArrayList<Dependency>();
			NodeList ndlDependency = document.getElementsByTagName("dependency");
			NodeList ndlGroupId = document.getElementsByTagName("groupId");
			NodeList ndlArtifactId = document.getElementsByTagName("artifactId");
			NodeList ndlVersion = document.getElementsByTagName("version");

			for (int i = 0; i < ndlGroupId.getLength(); i++) {
				Dependency dependency=null;
				
				Node depend = ndlDependency.item(0);
				Node group = ndlGroupId.item(i);
				Node artifact = ndlArtifactId.item(i);
				Node version = ndlVersion.item(i);
				
				if(Objects.equals(group.getParentNode().getNodeName(), depend.getNodeName())) {
					dependency = new Dependency();
					dependency.setGroupID(((Element) group).getTextContent());
				}
				if(Objects.equals(artifact.getParentNode().getNodeName(), depend.getNodeName())) {
					dependency.setArtifactId(((Element) artifact).getTextContent());
				}
				if(Objects.equals(version.getParentNode().getNodeName(), depend.getNodeName())) {
					dependency.setVersion(((Element) version).getTextContent());
				}
				if(dependency!=null) {
					dependences.add(dependency);
				}
			}
			System.out.println(dependences);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
