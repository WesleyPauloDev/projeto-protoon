package com.proton.services.municipe;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proton.models.entities.Endereco;
import com.proton.models.entities.Municipe;
import com.proton.models.repositories.MunicipeRepository;


@Component
public class MunicipeService {

    @Autowired // Para que o Spring faça essa injeção de Dependência do Repository
    private MunicipeRepository repository;

    //Método que retorna uma lista Json com todos os municipes
    public List<Municipe> findAll(){
        return repository.findAll();
    }

    //Método que retorna um objeto Json muncipe com base no id inserido
    public Municipe findById(Integer id){
        Optional<Municipe> obj = repository.findById(id);
        return obj.get();
    }

    //Método que insere um municipe novo no banco de dados, junto com o endereço
    public Municipe insert(Municipe obj) {
        Endereco endereco = obj.getEndereco();
        endereco.setMunicipe(obj);
        obj.setEndereco(endereco);
		return repository.save(obj);
	}

    // NÃO VAI DELETAR USUÁRIOS >>>> REGRA DE NEGÓCIO 
    // public void delete(Integer id) {
	// 	repository.deleteById(id);	
	// }
    

    //Método que atualiza as informações do municipe
    public Municipe update(Integer id, Municipe obj) {
		Municipe entity = repository.getReferenceById(id); //instancia o usuário sem mexer no banco de dados
		updateData(entity, obj);
			return repository.save(entity);
	}

    private void updateData(Municipe entity, Municipe obj) {
		// TODO Auto-generated method stub
        entity.setNome_municipe(obj.getNome_municipe());
		entity.setEmail(obj.getEmail());
        entity.setSenha(obj.getSenha());
        entity.setNum_CPF(obj.getNum_CPF());
        entity.setData_nascimento(obj.getData_nascimento());
        entity.setEndereco(obj.getEndereco());
	}

}
