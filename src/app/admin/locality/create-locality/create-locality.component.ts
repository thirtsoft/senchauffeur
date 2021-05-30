import { ToastrService } from 'ngx-toastr';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { LocalityService } from './../../../services/locality.service';
import { Locality, AddresseDto } from './../../../models/locality';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-create-locality',
  templateUrl: './create-locality.component.html',
  styleUrls: ['./create-locality.component.scss']
})
export class CreateLocalityComponent implements OnInit {

  formDataLocalityDTO: AddresseDto = new AddresseDto();
  deleteLocalityDTO: AddresseDto;

  constructor(private localiteService: LocalityService,
              private router: Router,
              private toastr: ToastrService,
              @Inject(MAT_DIALOG_DATA)  public data,
              public dialogRef:MatDialogRef<CreateLocalityComponent>,
  ){}

  ngOnInit(): void {}
  public onAddLocality() {
    this.localiteService.addLocalityDTO(this.formDataLocalityDTO).subscribe(
      (response: AddresseDto) => {
        this.dialogRef.close();
        this.toastr.success("Addresse Ajouté avec Succès");
        this.router.navigate(['/backend/admin/localities']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


}
