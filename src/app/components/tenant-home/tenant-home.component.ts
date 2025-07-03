import { HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-tenant-home',
  standalone: true,
  imports: [RouterModule, HttpClientModule],
  templateUrl: './tenant-home.component.html',
  styleUrl: './tenant-home.component.css'
})
export class TenantHomeComponent {

}
