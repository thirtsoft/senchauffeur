import { ChauffeurDto } from './chauffeur';
import { NewsleterDto } from './newsleter';
import { UtilisateurDto } from './utilisateur';
export class Email {
  id: number;
  from: string;
  to: string;
}

export class EmailDto {
  id: number;
  customerName: string;
  recipient: string;
  subject: string;
  message: string;

  createDate: Date;

  utilisateurDto: UtilisateurDto;
  chauffeurDto: ChauffeurDto;
  newsleterDto: NewsleterDto;

}
