import { Utilisateur } from './utilisateur';
import { Chauffeur } from './chauffeur';
export class Notation {
  id: number;
  reference: string;
  nbreEtoile: string;
  observation: string;

  chauffeur: Chauffeur;

  utilisateur: Utilisateur;

}
