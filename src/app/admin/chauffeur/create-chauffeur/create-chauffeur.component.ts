import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { PermisService } from './../../../services/permis.service';
import { PermisDto } from './../../../models/permis';
import { HttpErrorResponse, HttpEventType, HttpResponse } from '@angular/common/http';
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

  chauffeurPhotoFile;
  chauffeurCvFile;

  data;
  paramId :any = 0;
  mySubscription: any;

  editPhoto: boolean;
  editCv: boolean;
  currentProfile: any;
  selectedFiles;
  progress: number;
  currentPhotoFileUpload: any;
  currentCvFileUpload: any;
  currentTime: number = 0;
  id;

  userId;
  img: boolean;

  constructor(public chauffeurService: ChauffeurService,
              public permisService: PermisService,
              public addService: AddressService,
              private toastr: ToastrService,
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

  onAddChauffeur() {
    this.chauffeurService.addChauffeurDTO(this.formDataChauffeurDTO).subscribe(
      (response: ChauffeurDto) => {
        alert('Chauffeur created');
        this.router.navigate(['/admin/accueil/chauffeurs']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  onUpdateChauffeur() {
    this.chauffeurService.updateChauffeurDTO(this.formDataChauffeurDTO.id, this.formDataChauffeurDTO).subscribe(
      (response: ChauffeurDto) => {
        this.router.navigate(['/admin/accueil/chauffeurs']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  onSaveChauffeurWithFiles() {
    let formData = new FormData();
    this.currentPhotoFileUpload = this.chauffeurPhotoFile.item(0)
    this.currentCvFileUpload = this.chauffeurCvFile.item(0)
    console.log(this.currentPhotoFileUpload);
    console.log(this.currentCvFileUpload);
    
    formData.append('chauffeur', JSON.stringify(this.formDataChauffeurDTO));
    formData.append('photoChauffeur', this.currentPhotoFileUpload);
    formData.append('cvChauffeur', this.currentCvFileUpload);
    console.log("FormData--", formData);
    this.chauffeurService.addChauffeurWithPhotoAndCvFileInFolder(formData)
      .subscribe((response: ChauffeurDto)=> {
        console.log('Response--', response);
        this.toastr.success('avec succès','Chauffeur Ajoutée', {
          timeOut: 1500,
          positionClass: 'toast-top-right',
        });
        this.router.navigateByUrl("admin/accueil/chauffeurs").then(() => {
  
        });
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  getTS() {
    return this.currentTime;
  }

  onEditPhoto(p) {
    if(this.paramId  && this.paramId  > 0){
      this.paramId = p;
      this.editPhoto=true;
      this.editCv=true;
    }
    this.editPhoto=false;
    this.editCv=false;
  }

  onSelectPhotoFile(event) {
    this.chauffeurPhotoFile=event.target.files;
  } 

  processForm() {
    this.progress = 0;
    this.currentPhotoFileUpload = this.chauffeurPhotoFile.item(0)
    console.log(this.currentPhotoFileUpload);
    console.log(this.paramId);
    this.chauffeurService.uploadPhotoOfChauffeurInFolder(this.currentPhotoFileUpload, this.formDataChauffeurDTO.id)
      .subscribe(event => {
        if (event.type === HttpEventType.UploadProgress) {
          this.progress = Math.round(100 * event.loaded / event.total);
          this.toastr.success('avec succès','Photo remplacé', {
            timeOut: 1500,
            positionClass: 'toast-top-right',
          });
          this.router.navigateByUrl("admin/accueil/chauffeurs").then(() => {
  
          });
        } else if (event instanceof HttpResponse) {
          this.editPhoto=false;
          this.currentTime = Date.now();
        }
      }, err => {
        this.toastr.warning("Problème de chargment de la photo");
      }
    );
    this.chauffeurPhotoFile = undefined;
  }

  onSelectCvFile(event) {
    this.chauffeurCvFile=event.target.files;
  } 

  processCvForm() {
    this.progress = 0;
    this.currentCvFileUpload = this.chauffeurCvFile.item(0)
    console.log(this.currentCvFileUpload);
    console.log(this.paramId);
    this.chauffeurService.uploadCvOfChauffeurInFolder(this.currentCvFileUpload, this.formDataChauffeurDTO.id)
      .subscribe(event => {
        if (event.type === HttpEventType.UploadProgress) {
          this.progress = Math.round(100 * event.loaded / event.total);
          this.toastr.success('avec succès','CV remplacé', {
            timeOut: 1500,
            positionClass: 'toast-top-right',
          });
          this.router.navigateByUrl("admin/accueil/chauffeurs").then(() => {
  
          });
        } else if (event instanceof HttpResponse) {
          this.editCv=false;
          this.currentTime = Date.now();
        }
      }, err => {
        this.toastr.warning("Problème de chargment du cv");
      }
    );
    this.chauffeurCvFile = undefined;
  }


  goBack() {
    this.router.navigateByUrl("/admin/accueil/chauffeurs");
  }



}
