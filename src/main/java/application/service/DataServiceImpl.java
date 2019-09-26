package application.service;

import application.model.Data;
import application.model.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DataServiceImpl implements DataService{
    @Autowired
    DataRepository dataRepository;

    @Override
    public void addData(Data data) {
        dataRepository.save(data);
    }

    @Override
    public Optional<Data> findDataById(long id) {
        return dataRepository.findById(id);
    }

    @Override
    public List<Data> getAllData() {
        return dataRepository.findAll();
    }
}
