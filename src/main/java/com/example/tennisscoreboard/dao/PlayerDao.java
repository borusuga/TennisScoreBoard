package com.example.tennisscoreboard.dao;

import com.example.tennisscoreboard.models.Player;

import java.util.List;
import java.util.Optional;

import com.example.tennisscoreboard.utils.SessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class PlayerDao implements Dao<Player> {
    @Override
    public Player create(Player player) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.persist(player);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return player;
    }

    @Override
    public Optional<Player> getById(int id) {
        Player player = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            player = session.get(Player.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(player);
    }

    @Override
    public List<Player> getAll() {
        List<Player> players = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            players = session.createQuery("FROM Player", Player.class).getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }

    public Optional<Player> getByName(String name) {
        Player player = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            Query<Player> query = session.createQuery("from Player where name= :name", Player.class);
            query.setParameter("name", name);
            player = (Player) query.getSingleResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(player);
    }
}
