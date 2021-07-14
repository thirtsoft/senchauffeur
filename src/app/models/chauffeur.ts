import { AddresseDto } from './address';
import { Permis, PermisDto } from './permis';
export class Chauffeur {
  id: number;
  reference: string;
  firstName: string;
	lastName: string;
  sexe: string;
  addressActuel: string;
  email: string;
  phoneChauffeur: string;
  nbreAnneeExperience: number;
  pretentionSalaire: number;
  mobilite: string;
	cvChauffeur: string;
  photoChauffeur: string;

  permis: Permis;

}

export class ChauffeurDto {
  id: number;
  reference: string;
  firstName: string;
	lastName: string;
  sexe: string;
  addressActuel: string;
  email: string;
  phoneChauffeur: string;
  nbreAnneeExperience: number;
  pretentionSalaire: number;
  mobilite: string;
	cvChauffeur: string;
  photoChauffeur: string;

  permisDto: PermisDto;
  addresseDto: AddresseDto;


}
