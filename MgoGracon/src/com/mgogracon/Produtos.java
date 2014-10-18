package com.mgogracon;

public class Produtos {

	 // private variables
    int _id;
    String _descricao;
    double _valor;
    int _local;

    // Empty constructor
    public Produtos() {

    }

    // constructor
    public Produtos(int keyId, String descricao, double valor, int local) {
    this._id 		= keyId;
    this._descricao 	= descricao;
    this._local         = local;
    this._valor        	= valor;
    

    }
    
    public int getID() {
    return this._id;
    }

    public void setID(int keyId) {
    this._id = keyId;
    }
    
    public String getDescricao() {
    return this._descricao;
    }

    public void setDescricao(String descricao) {
    this._descricao = descricao;
    }
    
    public double getValor() {
        return this._valor;
        }

    public void setValor(double valor) {
        this._valor = valor;
        }
    
    public int getLocal() {
    return this._local;
    }

    // setting id
    public void setlocal(int local) {
    this._local = local;
    }
    
    
}
