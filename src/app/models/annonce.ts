import { Permis, PermisDto } from './permis';
import { Recruteur, RecruteurDto } from './recruteur';
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

export class AnnonceDto {
  id: number;
  reference: string;
  lieuPoste: string;
  salaire: string;
  dateCandidature: Date;
	dateCloture: Date;
  modeCandidature: string;

  statusAnnonce: StatusAnnonce

  permisDto: PermisDto;

	recruteurDto: RecruteurDto;
}
