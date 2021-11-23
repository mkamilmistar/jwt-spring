package com.jwt.userservice.model.entity;

import com.jwt.userservice.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {
  private static final long serialVersionUID = -7049957706738879274L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  private Collection<Role> roles = new ArrayList<>();
}
