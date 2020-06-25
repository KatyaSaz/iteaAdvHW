package parsers;

public class Dependency {
	
	private String groupID;
	private String artifactId;
	private String version;
	
	public Dependency() {
		super();
	}

	public Dependency(String groupID, String artifactId, String version) {
		super();
		this.groupID = groupID;
		this.artifactId = artifactId;
		this.version = version;
	}

	public String getGroupID() {
		return groupID;
	}

	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Dependency [groupID=" + groupID + ", artifactId=" + artifactId + ", version=" + version + "]";
	}
	
}
