import { UtilisateurDto, Utilisateur } from './utilisateur';

export class Jeton {
  id: number;
  montant: number;
  etat: string;
  createdDate: Date;

  utilisateur: Utilisateur;
}

export class JetonDto {
  id: number;
  montant: number;
  etat: string;
  createdDate: Date;

  utilisateurDto: UtilisateurDto;

}
