package domein;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

public class CategorieListener {

	@PostPersist
	private void afterInsert(Categorie categorie) {
		System.out.println("[CATEGORIE]: Added categorie " + categorie.getNaam());
	}

	@PostRemove
	private void afterRemove(Categorie categorie) {
		System.out.println("[CATEGORIE]: Removed categorie " + categorie.getNaam());
	}

	@PostUpdate
	private void afterUpdate(Categorie categorie) {
		System.out.println("[CATEGORIE]: Updated categorie " + categorie.getNaam());
	}
}
