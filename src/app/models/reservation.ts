import { Utilisateur, UtilisateurDto } from './utilisateur';
import { Chauffeur, ChauffeurDto } from './chauffeur';

export class Reservation {
  id: number;
  status: string;
  description: string;
  createdDate: Date;
  dateDemarrage: Date;

  chauffeur: Chauffeur;
  utilisateur: Utilisateur;

}

export class ReservationDto {
  id: number;
  status: string;
  description: string;
  createdDate: Date;
  dateDemarrage: Date;

  chauffeurDto: ChauffeurDto;
  utilisateurDto: UtilisateurDto;
}



