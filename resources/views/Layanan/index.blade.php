@extends('layouts.master')

@section('content')
{{-- <div class="panel-header panel-header-lg">
</div>
<div class="content">
  <div class="row">
    <div class="col-lg-12">
      <div class="card card-chart">
        <div class="card-header">
            <div class="col-12">
                <button type="button" class="btn btn-primary float-right" data-toggle="modal" data-target="#exampleModal">Tambah Layanan</button>
            </div>
            <div class="panel-body1">
              <table class="table">
                <thead>
                  <tr>
                    <th>NAMA</th>
                    <th>HARGA</th>
                    <th>AKSI</th>
                </tr>
                @foreach ($data_layanan as $layanan)
                <tr>
                    <td>{{$layanan->nama}}</td>
                    <td>{{$layanan->harga}}</td>
                    <td>
                        <a href="/Layanan/{{$layanan->id}}/delete" class="btn btn-danger" onclick="return confirm('Yakin Mau di Hapus?')"><i class="fa fa-trash"></i></a>
                    </td>
                </tr>    
                @endforeach
                 </tbody>
               </table>
               </div>
      </div>
    </div>
    </div>
  </div>
</div>
  </div>
</div>
    </div> --}}

    <div class="panel-header panel-header-sm">
    </div>
    <div class="content">
      <div class="row">
        <div class="col-md-12">
          <div class="card">
            <div class="card-header">
              <div class="col-lg-6">
              <h4 class="card-title">Menu Layanan</h4>
            </div>
              <div class="col-lg-12">
                <button type="button" class="btn btn-success float-right" data-toggle="modal" data-target="#exampleModal">Tambah Layanan</button>
            </div>
            </div>
           
            <div class="card-body">
              <div class="table-responsive">
                <table class="table" style="margin-top: 10dp;">
                  <thead class="text-primary">
                    <tr>
                      <th>NAMA</th>
                      <th>HARGA</th>
                      <th>AKSI</th>
                  </tr>
                  </thead>
                  @foreach ($data_layanan as $layanan)
                  <tr>
                      <td>{{$layanan->nama}}</td>
                      <td>{{$layanan->harga}}</td>
                      <td>
                          <a href="/Layanan/{{$layanan->id}}/delete" class="btn btn-danger" onclick="return confirm('Are you sure want to delete this??')"><i class="fa fa-trash"></i></a>
                      </td>
                  </tr>    
                  @endforeach
                   </tbody>
                </table>
            </div>
          </div>
        </div>


    <!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
            <form action="/Layanan/create" method="POST">
                @csrf
                <div class="form-group">
                  <label for="exampleInputEmail1">Nama</label>
                  <input name="nama" type="text" class="form-control" placeholder="Nama" id="exampleInputEmail1" aria-describedby="emailHelp">
                </div>
                <div class="form-group">
                  <label for="exampleInputEmail1">Harga</label>
                  <input name="harga" class="form-control" placeholder="Harga" type="text" id="exampleInputEmail1">
                </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
          <button type="submit" class="btn btn-primary">Save changes</button>
        </form>
        </div>
      </div>
    </div>

    
    @endsection