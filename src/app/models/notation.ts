import { Utilisateur, UtilisateurDto } from './utilisateur';
import { Chauffeur, ChauffeurDto } from './chauffeur';
export class Notation {
  id: number;
  reference: string;
  nbreEtoile: string;
  observation: string;

  chauffeur: Chauffeur;

  utilisateur: Utilisateur;

}

export class NotificationDto {
  id: number;
  reference: string;
  nbreEtoile: string;
  observation: string;

  chauffeurDto: ChauffeurDto;

  utilisateurDto: UtilisateurDto;
}
