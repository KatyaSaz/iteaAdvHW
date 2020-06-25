package parsers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DependencySaxParser extends DefaultHandler {

	private final String DEPENDENCY = "dependency";
	private final String GROUP_ID = "groupId";
	private final String ARTIFACT_ID = "artifactId";
	private final String VERSION = "version";

	private Dependency dependency;
	private boolean isDependency;
	private boolean isGroupId;
	private boolean isArtifactId;
	private boolean isVersion;

	private List<Dependency> dependencies = new ArrayList<Dependency>();

	public List<Dependency> getDependencies() {
		return dependencies;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (Objects.equals(qName, DEPENDENCY)) {
			dependency = new Dependency();
			isDependency = true;
		}
		if (Objects.equals(qName, GROUP_ID) && isDependency) {
			isGroupId = true;
		}
		if (Objects.equals(qName, ARTIFACT_ID) && isDependency) {
			isArtifactId = true;
		}
		if (Objects.equals(qName, VERSION) && isDependency) {
			isVersion = true;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String value = "";
		for (int i = start; i < start + length; i++) {
			value += ch[i];
		}

		if (value.length() > 0) {
			if (isGroupId) {
				dependency.setGroupID(value);
				isGroupId = false;
			}
			if (isArtifactId) {
				dependency.setArtifactId(value);
				isArtifactId = false;
			}
			if (isVersion) {
				dependency.setVersion(value);
				isVersion = false;
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (Objects.equals(qName, DEPENDENCY)) {
			isDependency = false;
			dependencies.add(dependency);
		}
	}
}
