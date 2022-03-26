import { Utilisateur, UtilisateurDto } from './utilisateur';
import { Chauffeur, ChauffeurDto } from './chauffeur';
export class Notation {
  id: number;
  nbreEtoile: string;
  observation: string;
  createdDate: Date;

  chauffeur: Chauffeur;

  utilisateur: Utilisateur;

}

export class NotationDto {
  id: number;
  nbreEtoile: string;
  observation: string;
  createdDate: Date;

  chauffeurDto: ChauffeurDto;

  utilisateurDto: UtilisateurDto;
}
