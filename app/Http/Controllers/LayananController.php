<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class LayananController extends Controller
{
    public function __construct()
    {
        $this->middleware(['auth']);
    }

    public function index()
    {
        $data_layanan = \App\Layanan::all();
        return view('Layanan.index', ['data_layanan' => $data_layanan]);
    }

    public function index2()
    {
        return view('Dashboard.dashboard');
    }

    public function create(Request $request)
    {
        \App\Layanan::create($request->all());
        return redirect('/Layanan');
    
    }

    public function store(Request $request)
    {
        //
    }

    public function show($id)
    {
        //
    }

    public function edit($id)
    {
        //
    }

    public function update(Request $request, $id)
    {
        //
    }

    public function delete($id)
    {
        $layanan = \App\Layanan::find($id);
        $layanan->delete($layanan);
        return redirect('/Layanan');
    }
}
