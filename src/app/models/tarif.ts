import { AnnonceDto } from './annonce';
export class TarifDto {
  id: number;
  reference: string;
  montantTarif: number;
  description: string;

  annonceDto: AnnonceDto;

}
