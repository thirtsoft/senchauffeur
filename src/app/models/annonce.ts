import { UtilisateurDto } from './utilisateur';
import { AddresseDto } from './locality';
import { VilleDto } from './ville';
import { Permis, PermisDto } from './permis';
import { Recruteur, RecruteurDto } from './recruteur';
import { StatusAnnonce } from './status';
export class Annonce {
  id: number;
  reference: string;
  libelle: string;
  lieuPoste: string;
  salaire: string;
  emailPoste: string;
  time: string;
  status: string;
  experience: string;
  typeContrat: string;
  description: string;
  dateCandidature: Date;
  dateCloture: Date;

	statusAnnonce: StatusAnnonce;

	permis: Permis;

	recruteur: Recruteur;

}

export class AnnonceDto {
  id: number;
  reference: string;
  libelle: string;
  lieuPoste: string;
  salaire: string;
  emailPoste: string;
  time: string;
  status: string;
  proExperience: string;
  typeContrat: string;
  description: string;
  dateCandidature: Date;
  dateCloture: Date;

  statusAnnonce: StatusAnnonce

  permisDto: PermisDto;

//  recruteurDto: RecruteurDto;

  addresseDto: AddresseDto;

  utilisateurDto: UtilisateurDto;


}
