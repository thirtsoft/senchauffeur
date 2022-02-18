import { Annonce, AnnonceDto } from './annonce';

export class HistoriqueAnnonce {
  id: number;
  action: string;
  createdDate: Date;

  annonce: Annonce;

}

export class HistoriqueAnnonceDto {
  id: number;
  action: string;
  createdDate: Date;

  annonceDto: AnnonceDto;
}

