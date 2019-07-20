package com.hzy.gdx.b2d.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.LinkedList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by huzongyao on 2018/10/7.
 */

public class FirstBodyGame extends Game {

    private Box2DDebugRenderer mDebugRender;
    private World mWorld;
    private OrthographicCamera mCamera;
    private float mWorldWidth = 8f;
    private float mWorldHeight;
    private float mBallRadius = 0.2f;
    private List<Body> mBallList;
    private List<Body> mCubeList;

    @Override
    public void create() {
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
        float dpm = width / mWorldWidth;
        mWorldHeight = height / dpm;

        mCamera = new OrthographicCamera(mWorldWidth, mWorldHeight);
        mCamera.position.set(mWorldWidth / 2f, mWorldHeight / 2f, 0);
        mCamera.update();

        mWorld = new World(new Vector2(0, -9.8f), true);
        mDebugRender = new Box2DDebugRenderer();

        mBallList = new LinkedList<>();
        mCubeList = new LinkedList<>();
        createBorder();
        createMixBodies();
    }

    public void createMixBodies() {
        for (int i = 0; i < 20; i++) {
            mBallList.add(createBallBody(i));
            mCubeList.add(createCubeBody(i));
        }
    }

    private Body createBallBody(int index) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        // set original position
        bodyDef.position.set(mWorldWidth / 2 + .1f, .1f * index + mWorldHeight * .8f);
        CircleShape shape = new CircleShape();
        shape.setRadius(mBallRadius + .02f * index);
        FixtureDef fixture = new FixtureDef();
        fixture.shape = shape;
        // so the body will move forever
        fixture.friction = .3f;
        fixture.restitution = .9f;
        fixture.density = .3f;
        Body body = mWorld.createBody(bodyDef);
        body.createFixture(fixture);
        return body;
    }

    private Body createCubeBody(int index) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        // set original position
        bodyDef.position.set(mWorldWidth / 2 + .1f * index, mWorldHeight * .8f);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(mBallRadius + .01f * index, mBallRadius + .01f * index);
        FixtureDef fixture = new FixtureDef();
        fixture.shape = shape;
        // so the body will move forever
        fixture.friction = .4f;
        fixture.restitution = .8f;
        fixture.density = 0.6f;
        Body body = mWorld.createBody(bodyDef);
        body.createFixture(fixture);
        body.applyForceToCenter(new Vector2(.1f, 0.3f), true);
        return body;
    }


    private void createBorder() {
        BodyDef bodyDef = new BodyDef();
        Body groundBody = mWorld.createBody(bodyDef);
        EdgeShape edge = new EdgeShape();
        FixtureDef boxShapeDef = new FixtureDef();
        boxShapeDef.shape = edge;
        // top
        edge.set(new Vector2(.1f, .1f), new Vector2(mWorldWidth - .1f, .1f));
        groundBody.createFixture(boxShapeDef);
        // left
        edge.set(new Vector2(.1f, .1f), new Vector2(.1f, mWorldHeight - .1f));
        groundBody.createFixture(boxShapeDef);
        // right
        edge.set(new Vector2(mWorldWidth - .1f, .1f),
                new Vector2(mWorldWidth - .1f, mWorldHeight - .1f));
        groundBody.createFixture(boxShapeDef);
        // bottom
        edge.set(new Vector2(.1f, mWorldHeight - .1f),
                new Vector2(mWorldWidth - .1f, mWorldHeight - .1f));
        groundBody.createFixture(boxShapeDef);
    }

    @Override
    public void render() {
        super.render();
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        mWorld.step(Gdx.app.getGraphics().getDeltaTime(), 8, 8);
        mDebugRender.render(mWorld, mCamera.combined);
    }
}
