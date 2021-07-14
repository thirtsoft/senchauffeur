import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { PermisService } from './../../../services/permis.service';
import { PermisDto } from './../../../models/permis';
import { HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';
import { ChauffeurService } from './../../../services/chauffeur.service';
import { ChauffeurDto } from './../../../models/chauffeur';
import { AddressService } from './../../../services/address.service';
import { AddresseDto } from './../../../models/address';
@Component({
  selector: 'app-create-chauffeur',
  templateUrl: './create-chauffeur.component.html',
  styleUrls: ['./create-chauffeur.component.scss']
})
export class CreateChauffeurComponent implements OnInit {

  formDataChauffeurDTO: ChauffeurDto = new ChauffeurDto();
  listPermisData: PermisDto[];
  listAddressDTO: AddresseDto[];

  chauffeurPhotoFile: any = File;
  chauffeurCvFile: any = File;

  data;
  paramId :any = 0;
  mySubscription: any;

  constructor(private chauffeurService: ChauffeurService,
              private permisService: PermisService,
              private addService: AddressService,
           //   private toastr: ToastrService,
              public dialog: MatDialog,
              private actRoute: ActivatedRoute,
              private router: Router,
  ){
    //--for reload componant
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.mySubscription = this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        // Trick the Router into believing it's last link wasn't previously loaded
        this.router.navigated = false;
      }
    });
  }

  ngOnInit(): void {
    this.paramId = this.actRoute.snapshot.paramMap.get('id');
    console.log('Param--', this.paramId);
    if(this.paramId  && this.paramId  > 0){
      this.getChauffeurDTOById(this.paramId);
    }

    this.getListPermisDTOs();

    this.getListAddresseDTOs();
  }

  getChauffeurDTOById(id: number) {
    console.log('getOne');
    this.chauffeurService.getChauffeurDTOById(id).subscribe(
      (response: ChauffeurDto) => {
        console.log('data--', response);
        this.formDataChauffeurDTO = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

  getListPermisDTOs() {
    this.permisService.getPermisDTOs().subscribe(
      (response: PermisDto[]) => {
        this.listPermisData = response;
      }, (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }
  getListAddresseDTOs() {
    this.addService.getAddresseDtos().subscribe(
      (response: AddresseDto[]) => {
        this.listAddressDTO = response;
      }, (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  public onAddChauffeur() {
    this.chauffeurService.addChauffeurDTO(this.formDataChauffeurDTO).subscribe(
      (response: ChauffeurDto) => {
  //      this.dialogRef.close();
    //    this.toastr.success("Chauffeur Ajouté avec Succès");
        this.router.navigate(['/backend/admin/chauffeurs']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onUpdateChauffeur() {
    this.chauffeurService.updateChauffeurDTO(this.formDataChauffeurDTO.id, this.formDataChauffeurDTO).subscribe(
      (response: ChauffeurDto) => {
  //      this.dialogRef.close();
  //      this.toastr.warning("Chauffeur Update avec Succès");
        this.router.navigate(['/backend/admin/chauffeurs']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  onSelectPhotoFile(event) {
    const file = event.target.files[0];
    this.chauffeurPhotoFile = file;
  }

  onSelectCvFile(event) {
    const file = event.target.files[0];
    this.chauffeurCvFile = file;
  }

   // Ajouter un produits avec sa photo
  onSaveChauffeurWithFiles() {
    let formData = new FormData();
    formData.append('chauffeur', JSON.stringify(this.formDataChauffeurDTO));
    console.log("Chauffeur--", this.formDataChauffeurDTO);

    formData.append('photoChauffeur', this.chauffeurPhotoFile);
    formData.append('cvChauffeur', this.chauffeurCvFile);
    console.log("FormData--", formData);
    this.chauffeurService.addChauffeurDTOWithFiles(formData)
      .subscribe((response: ChauffeurDto)=> {
        console.log('Response--', response);
    //    this.toastr.success("Chauffeur Ajouté avec Succès");
        this.router.navigate(['/backend/admin/chauffeurs']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }



}
