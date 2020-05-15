<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class ModelController extends Controller
{
    public function __construct()
    {
        $this->middleware(['auth']);
    }

    public function index()
    {
        $data_model = \App\Model1::all();
        return view('Model.index', ['data_model' => $data_model]);
    }

    public function index2()
    {
        return view('Dashboard.dashboard');
    }

    public function create(Request $request)
    {
        \App\Model1::create($request->all());
        return redirect('/Model');
    
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
        $model = \App\Model1::find($id);
        $model->delete($model);
        return redirect('/Model');
    }
}
