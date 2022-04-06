package domein;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;

public class DatasourceListener {
	
	@PostPersist
	private void afterInsert(Datasource datasource) {
		System.out.println("[DATASOURCE]: Added datasource " + datasource.getNaam());	
	}	
	
	@PostRemove
	private void afterRemove(Datasource datasource) {
		System.out.println("[DATASOURCE]: Removed datasource " + datasource.getNaam());
	}
}
