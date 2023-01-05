package registration.registrationsystem.Util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListMapper <T,E>{

    @Autowired
    ModelMapper modelMapper;

    public List<?> mapListToDto(List<T> list, E e){
        return list
                .stream()
                .map(t -> modelMapper.map(t, e.getClass()))
                .collect(Collectors.toList());
    }

    public E mapClassToDto(T t , E e){
        return (E) modelMapper.map(t, e.getClass());
    }

    public T mapClassFromDto(E e , T t){
        return (T) modelMapper.map(e, t.getClass());
    }
}
