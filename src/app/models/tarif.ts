import { TypeAnnonceDto } from './type-annonce';

export class TarifDto {
  id: number;
  reference: string;
  montantTarif: number;
  description: string;

  typeAnnonceDto: TypeAnnonceDto;

}
