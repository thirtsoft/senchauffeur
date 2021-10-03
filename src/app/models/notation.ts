import { Utilisateur, UtilisateurDto } from './utilisateur';
import { Chauffeur, ChauffeurDto } from './chauffeur';
export class Notation {
  id: number;
  nbreEtoile: string;
  observation: string;

  chauffeur: Chauffeur;

  utilisateur: Utilisateur;

}

export class NotationDto {
  id: number;
  nbreEtoile: string;
  observation: string;

  chauffeurDto: ChauffeurDto;

  utilisateurDto: UtilisateurDto;
}
