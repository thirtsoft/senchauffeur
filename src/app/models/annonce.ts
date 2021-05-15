import { Permis } from './permis';
import { Recruteur } from './recruteur';
import { StatusAnnonce } from './status';
export class Annonce {
  id: number;
  reference: string;
  lieuPoste: string;
  salaire: string;
  dateCandidature: Date;
	dateCloture: Date;
  modeCandidature: string;

	statusAnnonce: StatusAnnonce;

	permis: Permis;

	recruteur: Recruteur;

}
