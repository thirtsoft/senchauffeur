import { Component, OnInit, Inject } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';

import { ToastrService } from 'ngx-toastr';
import { PermisService } from './../../../services/permis.service';
import { PermisDto } from './../../../models/permis';

@Component({
  selector: 'app-create-permis',
  templateUrl: './create-permis.component.html',
  styleUrls: ['./create-permis.component.scss']
})
export class CreatePermisComponent implements OnInit {

  formDataPermisDTO: PermisDto = new PermisDto();

  data;
  paramId :any = 0;
  mySubscription: any;

  constructor(private permisService: PermisService,
              private router: Router,
<<<<<<< HEAD
=======
//              private toastr: ToastrService,
>>>>>>> dev
              public dialog: MatDialog,
              private actRoute: ActivatedRoute,

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
      this.getPermisDTOById(this.paramId);
    }
  }

  getPermisDTOById(id: number) {
    console.log('getOne');
    this.permisService.getPermisDTOById(id).subscribe(
      (response: PermisDto) => {
        console.log('data--', response);
        this.formDataPermisDTO = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

  public onAddPermis() {
    this.permisService.addPermisDTO(this.formDataPermisDTO).subscribe(
      (response: PermisDto) => {
<<<<<<< HEAD
        this.router.navigate(['/backend/admin/permis']);
=======
  //      this.dialogRef.close();
  //      this.toastr.success("Permis Ajouté avec Succès");
        this.router.navigate(['/backend/admin/listPermis']);
>>>>>>> dev
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onUpdatePermis() {
    this.permisService.updatePermisDTO(this.formDataPermisDTO.id, this.formDataPermisDTO).subscribe(
      (response: PermisDto) => {
<<<<<<< HEAD
        this.router.navigate(['/backend/admin/permis']);
=======
  //      this.dialogRef.close();
  //      this.toastr.warning("Permis Update avec Succès");
        this.router.navigate(['/backend/admin/listPermis']);
>>>>>>> dev
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


}
