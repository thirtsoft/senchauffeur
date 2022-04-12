import { Role } from './role';
export class Utilisateur {
  id: number;
  name: string;
  username: string;
  mobile: string;
  email: string;
  nomEntreprise: string;
  addressRecruteur: string;
  villeRecruteur: string;
  website: string;
  secteurActivite: string;
  information: string;
  active: boolean;
  photo: string;
  password: string;

}

export class UtilisateurDto {
  id: number;
  name: string;
  username: string;
  mobile: string;
  email: string;
  nomEntreprise: string;
  addressRecruteur: string;
  villeRecruteur: string;
  website: string;
  secteurActivite: string;
  information: string;
  photo: string;
  active: boolean;
  password: string;

  roles?: Role[];
}
